package com.flexpag.microservicepagamento.config.exceptions.handler;

import com.flexpag.microservicepagamento.config.exceptions.DataValidationExceptionDto;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public final String handlerErrorEntityNotFound(EntityNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final Stream<DataValidationExceptionDto> handlerErrorBadRequest(MethodArgumentNotValidException ex){

        List<FieldError> listErrors = ex.getFieldErrors();
        return listErrors.stream().map((errors) -> new DataValidationExceptionDto(errors.getField(), errors.getDefaultMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public final String handlerErrorCepNotFound(IllegalArgumentException ex){
        return ex.getMessage();
    }
}
