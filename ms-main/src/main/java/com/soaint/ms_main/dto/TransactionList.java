package com.soaint.ms_main.dto;

import com.soaint.ms_main.feing.client.dto.TransactionsResponse;

import java.util.List;

public record TransactionList(
        boolean isFirstPage,
        boolean isLastPage,
        List<TransactionsResponse>  transactions
) {
}
