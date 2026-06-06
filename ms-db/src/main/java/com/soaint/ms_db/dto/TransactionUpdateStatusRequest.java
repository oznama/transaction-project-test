package com.soaint.ms_db.dto;

import com.soaint.ms_db.constant.TransactionStatus;

import java.math.BigDecimal;

public record TransactionUpdateStatusRequest(
        Long id,
        BigDecimal referencia,
        TransactionStatus estatus
) {
}
