package com.soaint.ms_db.service;

import com.soaint.ms_db.dto.TransactionRequest;
import com.soaint.ms_db.dto.TransactionResponse;
import com.soaint.ms_db.dto.TransactionUpdateStatusRequest;
import com.soaint.ms_db.dto.TransactionsResponse;
import com.soaint.ms_db.exception.TransactionNotCreatedException;
import com.soaint.ms_db.exception.TransactionStatusNotUpdatedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    TransactionResponse saveTransaction(TransactionRequest transactionRequest) throws TransactionNotCreatedException;
    void updateTransactionStatus(TransactionUpdateStatusRequest transactionRequest) throws TransactionStatusNotUpdatedException;
    List<TransactionsResponse> getTransactions(Pageable pageable);
}
