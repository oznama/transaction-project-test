package com.soaint.ms_auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Especificacion de inicio de sesion")
public record LoginRequest(
        @JsonProperty("usuario")
        @Schema(description = "Nombre de usuario", example = "admin")
        String username,
        @Schema(description = "Contraseña", example = "password123")
        String password
) {
}
