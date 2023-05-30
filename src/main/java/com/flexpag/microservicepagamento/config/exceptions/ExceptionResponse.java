package com.flexpag.microservicepagamento.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3388679486386469921L;

    private Date timestamp;
    private String message;
    private String details;

}
