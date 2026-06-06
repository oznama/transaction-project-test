package com.soaint.ms_db.service.impl;

import com.soaint.ms_db.constant.TransactionStatus;
import com.soaint.ms_db.dto.*;
import com.soaint.ms_db.exception.TransactionNotCreatedException;
import com.soaint.ms_db.exception.TransactionStatusNotUpdatedException;
import com.soaint.ms_db.mapper.TransactionMapper;
import com.soaint.ms_db.model.Transaction;
import com.soaint.ms_db.repository.TransactionRepository;
import com.soaint.ms_db.service.TransactionService;
import com.soaint.ms_db.util.TransactionReferenceRandomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponse saveTransaction(TransactionRequest transactionRequest) throws TransactionNotCreatedException {
        log.debug("Creating transaction ...");
        try {
            return processTransaction(transactionRequest);
        } catch (Exception e) {
            throw new TransactionNotCreatedException("Error al crear la transaccion: " + e.getMessage());
        }
    }

    @Override
    public void updateTransactionStatus(TransactionUpdateStatusRequest transactionRequest) throws TransactionStatusNotUpdatedException {
        log.debug("Updating transaction status {}...", transactionRequest);
        int updatedRows = transactionRepository.updateStatus(transactionRequest.id(), transactionRequest.referencia(), transactionRequest.estatus().getStatus());
        if ( updatedRows != 1) {
            throw new TransactionStatusNotUpdatedException("Error al actualizar la transaccion " + transactionRequest.id());
        }
    }

    @Override
    public List<TransactionsResponse> getTransactions(Pageable pageable) {
        log.info("Getting transactions with page {}...", pageable);
        return transactionRepository.findAll(pageable).map(transactionMapper::toTransactionsResponse).getContent();
    }

    /**
     * Crea transaccion
     * @param transactionRequest a guardar
     * @return respuesta con id de transaccion
     */
    private TransactionResponse processTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        transactionRepository.save(transaction);
        log.debug("Transaction created successfully with id : {}", transaction.getId());
        finalizeTransaction(transaction);
        return transactionMapper.toDto(transaction);
    }

    /**
     * Finaliza la trasaccion generando numero de referencia y estatus Aprobada
     * @param transaction
     */
    private void finalizeTransaction(Transaction transaction) {
        try {
            transaction.setReference(getRandomBigDecimal());
            transaction.setStatus(TransactionStatus.Aprobada.name());
            log.debug("Set reference {} to transaction {}", transaction.getReference(), transaction.getId());
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new TransactionNotCreatedException("Error al finalizar la transaccion: " + e.getMessage());
        }

    }

    /**
     * Genera un numero para referencia y valida que no exista en base de datos,
     * si existe genera otro numero hasta encontrar uno unico
     * @return Numero de referencia
     */
    private BigDecimal getRandomBigDecimal() {
        var bigDecimal = BigDecimal.ZERO;
        AtomicBoolean isTrying = new AtomicBoolean(true);
        while (isTrying.get()) {
            int intRandom = TransactionReferenceRandomizer.getInt();
            log.debug("Random int generated : {}, checking if exits", intRandom);
            bigDecimal = new BigDecimal(intRandom);
            transactionRepository.findByReference(bigDecimal).ifPresentOrElse(
                    t -> log.debug("Reference {} already exists, generating another one", intRandom),
                    () -> {
                        log.debug("Reference {} is unique, using it for transaction", intRandom);
                        isTrying.set(false);
                    }
            );
        }
        return bigDecimal;
    }
}
