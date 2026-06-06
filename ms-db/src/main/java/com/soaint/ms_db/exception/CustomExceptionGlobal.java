package com.soaint.ms_db.exception;

import com.soaint.ms_db.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionGlobal {

    @ExceptionHandler(TransactionNotCreatedException.class)
    public ResponseEntity<GenericResponse> handleTransactionNoCreatedException(TransactionNotCreatedException e) {
        log.error("Catching message transaction not created");
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Transaccion no creada", e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Catching message not readable exception");
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Peticion invalida", null));
    }
}
