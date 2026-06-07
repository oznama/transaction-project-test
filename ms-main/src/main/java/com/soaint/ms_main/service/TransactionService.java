package com.soaint.ms_main.service;

import com.soaint.ms_main.dto.*;

public interface TransactionService {
    TransactionResponse createPost(TransactionRequest transactionRequest);
    GenericResponse updateTransactionStatus(TransactionUpdateStatusRequest updateStatusRequest);
    TransactionList getTransactions(int page, int size, String sort);
}
