package com.soaint.ms_db.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Especificacion de una transaccion creada")
public record TransactionResponse(
        @Schema(description = "Identificador unico de la transaccion", example = "1")
        Long id,
        @Schema(description = "Estatus de la transaccion", example = "Aprobada")
        String estatus,
        @Schema(description = "Referencia de la transaccion", example = "948153")
        String referencia,
        @Schema(description = "Tipo de operacion", example = "venta")
        String operacion) {
}
