package com.soaint.ms_main.service;

import com.soaint.ms_main.dto.*;

import java.util.List;

public interface TransactionService {
    TransactionResponse createPost(TransactionRequest transactionRequest);
    GenericResponse updateTransactionStatus(TransactionUpdateStatusRequest updateStatusRequest);
    List<TransactionsResponse> getTransactions(int page, int size, String sort);
}
