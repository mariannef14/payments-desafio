package com.flexpag.microservicepagamento.model.dto.purchase;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PurchaseDto(

        @NotNull
        Long amount,

        @NotNull
        Long invoiceAmount,

        @NotNull
        Double rate,

        @NotNull
        Long clientId,
        
        List<Long> invoices_id) {}


