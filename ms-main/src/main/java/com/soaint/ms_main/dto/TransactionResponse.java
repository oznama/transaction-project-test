package com.soaint.ms_main.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de transaccion")
public record TransactionResponse(
        @Schema(description = "Identificador de la transaccion", example = "1")
        Long id,
        @Schema(description = "Importe de la transaccion", example = "100.00")
        String estatus,
        @Schema(description = "Referencia de la transaccion", example = "954730")
        String referencia,
        @Schema(description = "Tipo de operacion", example = "venta")
        String operacion) {
}
