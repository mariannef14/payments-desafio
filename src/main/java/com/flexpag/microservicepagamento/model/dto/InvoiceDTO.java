package com.flexpag.microservicepagamento.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record InvoiceDTO(

        LocalDate dueDate,

        @NotBlank
        String barcode,

        Long amount,

        boolean paid,

        Long contractNumber) {
}
