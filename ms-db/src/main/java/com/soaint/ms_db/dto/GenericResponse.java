package com.soaint.ms_db.dto;

public record GenericResponse(int code, String message, Object entity) {
}
