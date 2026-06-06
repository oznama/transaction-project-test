package com.soaint.ms_main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soaint.ms_main.constants.Regex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Especificacion de transacion a guardar")
public record TransactionRequest(
        @NotBlank(message = "Requerido")
        @Size(max = 8, message = "Operacion maximo de {max}")
        @Pattern(regexp = Regex.ONLY_CHARS, message = "Solo caracteres")
        @Schema(description = "Tipo de operacion", example = "venta")
        String operacion,
        @NotBlank(message = "Requerido")
        @Pattern(regexp = Regex.ONLY_CURRENCY, message = "Importe invalido, debe cumplir el formato ######.## y maximo 999999.99")
        @Schema(description = "Importe de la transaccion", example = "100.00")
        String importe,
        @NotBlank(message = "Requerido")
        @Schema(description = "Nombre del cliente", example = "Angel")
        @Pattern(regexp = Regex.ONLY_CHARS_WITH_SPACE, message = "Solo caracteres y espacios")
        @Size(min = 4, max = 10, message = "Nombre cliente invalido minimo {min} y maximo {max}")
        String cliente,
        @NotBlank(message = "Requerido")
        @Pattern(regexp = Regex.PATTERN_SECRET, message = "Secreto invalido, solo caracteres alfanumericos")
        @Size(max = 20, message = "Secreto maximo de {max}")
        String secreto
) {
}
