package com.soaint.ms_main.feing.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransactionsResponse(
        Long id,
        @JsonProperty("operacion")
        String operation,
        @JsonProperty("importe")
        Double amount,
        @JsonProperty("cliente")
        String customer,
        @JsonProperty("referencia")
        BigDecimal reference,
        @JsonProperty("estatus")
        String status
) {
}
