package com.soaint.ms_db.exception;

public class TransactionNotCreatedException extends RuntimeException {
    public TransactionNotCreatedException(String message) {
        super(message);
    }
}
