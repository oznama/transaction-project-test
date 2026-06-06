package com.soaint.ms_main.dto;

import com.soaint.ms_main.constants.TransactionStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record TransactionUpdateStatusRequest(
        @NotNull(message = "El id de la transacción es obligatorio")
        Long id,
        @NotNull(message = "La referencia de la transacción es obligatorio")
        @Digits(integer = 6, fraction = 0, message = "Valor debe ser de 6 digitos")
        @DecimalMin(value = "100000", inclusive = true, message = "El valor no puede ser menor a {value}")
        @DecimalMax(value = "999999", inclusive = true, message = "El valor no puede exceder {value}")
        BigDecimal referencia,
        @NotNull(message = "El estatus de la transacción es obligatorio")
        TransactionStatus estatus
) {
}
