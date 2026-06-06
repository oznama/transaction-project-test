package com.soaint.ms_db.constant;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    Aprobada("Aprobada"),
    cancelar("Cancelada");

    private final String status;

    TransactionStatus(String status) {
        this.status = status;
    }
}
