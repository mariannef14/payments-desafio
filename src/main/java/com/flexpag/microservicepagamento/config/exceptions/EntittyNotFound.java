package com.flexpag.microservicepagamento.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntittyNotFound extends RuntimeException {

    public EntittyNotFound(String clienteNãoExiste) {
        super(clienteNãoExiste);
    }
}
