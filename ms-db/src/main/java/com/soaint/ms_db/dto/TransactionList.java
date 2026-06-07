package com.soaint.ms_db.dto;

import java.util.List;

public record TransactionList(
        boolean isFirstPage,
        boolean isLastPage,
        List<TransactionsResponse>  transactions
) {
}
