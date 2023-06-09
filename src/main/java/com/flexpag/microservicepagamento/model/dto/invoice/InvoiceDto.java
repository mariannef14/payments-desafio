package com.flexpag.microservicepagamento.model.dto.invoice;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;


public record InvoiceDto(

        @JsonFormat(pattern="dd/MM/yyyy")
        LocalDate dueDate,

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String barcode,

        @NotNull(message = "Este campo não pode ser nulo")
        Long amount,

        @NotNull(message = "Este campo não pode ser nulo")
        Boolean paid,

        @NotNull(message = "Este campo não pode ser nulo")
        Long contractNumber) {
}
