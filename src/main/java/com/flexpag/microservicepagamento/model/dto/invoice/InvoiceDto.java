package com.flexpag.microservicepagamento.model.dto.invoice;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;


public record InvoiceDto(

        @JsonFormat(pattern="dd/MM/yyyy")
        LocalDate dueDate,

        @NotBlank
        String barcode,

        @NotNull
        Long amount,

        Boolean paid,

        @NotNull
        Long contractNumber) {
}
