package com.soaint.ms_main.controller;

import com.soaint.ms_main.dto.*;
import com.soaint.ms_main.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> saveTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        log.info("Procesando transaccion");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createPost(transactionRequest));
    }

    @PatchMapping
    public ResponseEntity<GenericResponse> updateTransactionStatus(@Valid @RequestBody TransactionUpdateStatusRequest updateStatusRequest) {
        log.info("Actualizando estatus de transaccion");
        transactionService.updateTransactionStatus(updateStatusRequest);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Estatus de transaccion actualizado", updateStatusRequest.id()));
    }

    @GetMapping
    public ResponseEntity<List<TransactionsResponse>> getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        log.info("Recuperando todos los transacciones");
        return ResponseEntity.ok(transactionService.getTransactions(page, size, sort));
    }
}
