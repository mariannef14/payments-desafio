package com.flexpag.microservicepagamento.model.dto;

public record PurchaseDTO(

        Long amount,

        Long invoiceAmount,

        Double rate,

        Long clientId) {}


