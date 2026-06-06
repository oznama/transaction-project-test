package com.soaint.ms_db.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Especificacion de una transaccion a crear")
public record TransactionRequest(
        @Schema(description = "Tipo de operacion", example = "venta")
        String operation,
        @Schema(description = "Importe de la transaccion", example = "100.00")
        Double amount,
        @Schema(description = "Nombre del cliente", example = "Angel")
        String customer,
        @Schema(description = "Secreto")
        String secret
) {
}
