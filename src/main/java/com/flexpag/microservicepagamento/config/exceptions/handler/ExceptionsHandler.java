package com.flexpag.microservicepagamento.config.exceptions.handler;

import com.flexpag.microservicepagamento.config.exceptions.DataValidationExceptionDto;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity handlerErrorNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity handlerErrorBadRequest(MethodArgumentNotValidException ex){

        List<FieldError> listErrors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(listErrors.stream().map((errors) -> new DataValidationExceptionDto(errors.getField(), errors.getDefaultMessage())));
        //return ResponseEntity.badRequest().body(errors.stream().map(DataValidationExceptionDto::new));
    }
}
