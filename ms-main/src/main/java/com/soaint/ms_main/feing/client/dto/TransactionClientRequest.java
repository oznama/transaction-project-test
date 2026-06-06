package com.soaint.ms_main.feing.client.dto;

public record TransactionClientRequest(
        String operation,
        Double amount,
        String customer,
        String secret
) {
}
