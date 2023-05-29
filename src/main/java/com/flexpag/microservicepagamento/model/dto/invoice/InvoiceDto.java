package com.flexpag.microservicepagamento.model.dto.invoice;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


public record InvoiceDto(

        @JsonFormat(pattern="dd/MM/yyyy")
        LocalDate dueDate,

        @NotBlank
        String barcode,

        Long amount,

        Boolean paid,

        Long contractNumber) {
}
