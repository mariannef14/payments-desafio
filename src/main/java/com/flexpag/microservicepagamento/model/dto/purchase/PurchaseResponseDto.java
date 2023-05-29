package com.flexpag.microservicepagamento.model.dto.purchase;

import java.util.List;

import com.flexpag.microservicepagamento.model.entities.Purchase;

public record PurchaseResponseDto(
    
    Long id,

    Long amount,

    Long invoiceAmount,

    Double rate,

    Long clientId,
    
    List<Long> invoices_id){

    public PurchaseResponseDto(Purchase purchase) {
        this(purchase.getId(), purchase.getAmount(), purchase.getInvoiceAmount(), purchase.getRate(),
         purchase.getClient().getId(), purchase.getInvoices().stream().map((p) -> p.getId()).toList());
    }
}

