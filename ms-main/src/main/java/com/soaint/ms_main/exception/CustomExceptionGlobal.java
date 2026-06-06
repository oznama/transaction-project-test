package com.soaint.ms_main.exception;

import com.soaint.ms_main.dto.ErrorDetailResponse;
import com.soaint.ms_main.dto.ErrorResponse;
import com.soaint.ms_main.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class CustomExceptionGlobal {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Catching message not readable exception");
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Peticion invalida", null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Catching method argument not valid exception");
        List<ErrorDetailResponse> errors = e.getBindingResult().getAllErrors().stream()
                .map((error) -> new ErrorDetailResponse(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors));
    }
}
