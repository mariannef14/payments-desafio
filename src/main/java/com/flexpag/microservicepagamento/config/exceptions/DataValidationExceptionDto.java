package com.flexpag.microservicepagamento.config.exceptions;

import org.springframework.validation.FieldError;

public record DataValidationExceptionDto(String field, String message) {

    public DataValidationExceptionDto(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
