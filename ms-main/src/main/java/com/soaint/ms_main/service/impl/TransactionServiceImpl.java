package com.soaint.ms_main.service.impl;

import com.soaint.ms_main.dto.*;
import com.soaint.ms_main.feing.client.TransactionClient;
import com.soaint.ms_main.feing.client.dto.TransactionClientRequest;
import com.soaint.ms_main.mapper.TransactionMapper;
import com.soaint.ms_main.service.Aes256Encrypter;
import com.soaint.ms_main.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionClient transactionClient;
    private final TransactionMapper transactionMapper;
    private final Aes256Encrypter aes256Encrypter;

    public TransactionServiceImpl(
            TransactionClient transactionClient,
            TransactionMapper transactionMapper,
            Aes256Encrypter aes256Encrypter) {
        this.transactionClient = transactionClient;
        this.transactionMapper = transactionMapper;
        this.aes256Encrypter = aes256Encrypter;
    }

    @Override
    public TransactionResponse createPost(TransactionRequest transactionRequest) {
        log.debug("Creating transaction request: {}, {} {}", transactionRequest.cliente(), transactionRequest.operacion(), transactionRequest.importe());
        var transactionClientRequest = transactionMapper.toClientRequest(transactionRequest);
        try {
            String secretDecrypted = this.aes256Encrypter.decrypt(transactionRequest.secreto());
            transactionClientRequest = new TransactionClientRequest(
                    transactionClientRequest.operation(),
                    transactionClientRequest.amount(),
                    transactionClientRequest.customer(),
                    secretDecrypted);
        } catch (Exception e) {
            log.warn("Impossible decrypt secret", e);
        }
        return transactionClient.createPost(transactionClientRequest);
    }

    @Override
    public GenericResponse updateTransactionStatus(TransactionUpdateStatusRequest updateStatusRequest) {
        log.debug("Updating transaction status for ID: {}, reference: {}", updateStatusRequest.id(), updateStatusRequest.referencia());
        return transactionClient.updateTransactionStatus(updateStatusRequest);
    }

    @Override
    public TransactionList getTransactions(int page, int size, String sort) {
        log.debug("Retrieving transactions for request: {}, {}, {} {}", page, size, sort);
        return transactionClient.getTransactions(page, size, sort);
    }
}
