package com.soaint.ms_db.controller;

import com.soaint.ms_db.dto.*;
import com.soaint.ms_db.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Crear transaccion", description = "Servicio para registrar transaccion en base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaccion creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class))),
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        log.info("Procesando transaccion");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.saveTransaction(transactionRequest));
    }

    @PatchMapping
    public ResponseEntity<GenericResponse> updateTransaction(@RequestBody TransactionUpdateStatusRequest transactionRequest) {
        log.info("Actualizando estatus de transaccion");
        transactionService.updateTransactionStatus(transactionRequest);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(),"Estatus de transaccion actualizado", transactionRequest.id()));
    }

    @GetMapping
    public ResponseEntity<List<TransactionsResponse>> getTransactions(Pageable pageable) {
        log.info("Recuperando transacciones");
        return ResponseEntity.ok(transactionService.getTransactions(pageable));
    }
}
