package com.soaint.ms_db.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transacciones")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long id;

    @Column(name = "operacion", nullable = false,  length = 10)
    private String operation;

    @Column(name = "importe", nullable = false)
    private Double amount;

    @Column(name = "cliente", nullable = false)
    private String customer;

    @Digits(integer = 6, fraction = 0)
    @Min(value = 100000)
    @Max(value = 999999)
    @Column(name = "referencia", precision = 6, scale = 0)
    private BigDecimal reference;

    @Column(name = "estatus")
    private String status;

    @Column(name = "secreto")
    private String secret;
}
