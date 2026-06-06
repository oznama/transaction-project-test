package com.soaint.ms_main.service.impl;

import com.soaint.ms_main.dto.*;
import com.soaint.ms_main.feing.client.TransactionClient;
import com.soaint.ms_main.mapper.TransactionMapper;
import com.soaint.ms_main.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionClient transactionClient;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionClient transactionClient, TransactionMapper transactionMapper) {
        this.transactionClient = transactionClient;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponse createPost(TransactionRequest transactionRequest) {
        log.debug("Creating transaction request: {}, {} {}", transactionRequest.cliente(), transactionRequest.operacion(), transactionRequest.importe());
        return transactionClient.createPost(transactionMapper.toClientRequest(transactionRequest));
    }

    @Override
    public GenericResponse updateTransactionStatus(TransactionUpdateStatusRequest updateStatusRequest) {
        log.debug("Updating transaction status for ID: {}, reference: {}", updateStatusRequest.id(), updateStatusRequest.referencia());
        return transactionClient.updateTransactionStatus(updateStatusRequest);
    }

    @Override
    public List<TransactionsResponse> getTransactions(int page, int size, String sort) {
        log.debug("Retrieving transactions for request: {}, {}, {} {}", page, size, sort);
        return transactionClient.getTransactions(page, size, sort);
    }
}
