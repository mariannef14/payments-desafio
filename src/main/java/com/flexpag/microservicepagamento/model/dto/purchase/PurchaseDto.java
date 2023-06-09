package com.flexpag.microservicepagamento.model.dto.purchase;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PurchaseDto(

        @NotNull(message = "Este campo não pode ser nulo")
        Long amount,

        @NotNull(message = "Este campo não pode ser nulo")
        Long invoiceAmount,

        @NotNull(message = "Este campo não pode ser nulo")
        Double rate,

        @NotNull(message = "Este campo não pode ser nulo")
        Long clientId,
        @NotNull(message = "Este campo não pode ser nulo")
        List<Long> invoices_id) {}


