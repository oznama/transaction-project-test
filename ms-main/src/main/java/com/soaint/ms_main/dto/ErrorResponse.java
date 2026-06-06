package com.soaint.ms_main.dto;

import java.util.List;

public record ErrorResponse(
        int code,
        List<ErrorDetailResponse> errors
) {
}
