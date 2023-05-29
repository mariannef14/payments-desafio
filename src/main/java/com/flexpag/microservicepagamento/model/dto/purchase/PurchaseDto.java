package com.flexpag.microservicepagamento.model.dto.purchase;

import java.util.List;

public record PurchaseDto(

        Long amount,

        Long invoiceAmount,

        Double rate,

        Long clientId,
        
        List<Long> invoices_id) {}


