package com.soaint.ms_db.exception;

public class TransactionStatusNotUpdatedException extends RuntimeException {
    public TransactionStatusNotUpdatedException(String message) {
        super(message);
    }
}
