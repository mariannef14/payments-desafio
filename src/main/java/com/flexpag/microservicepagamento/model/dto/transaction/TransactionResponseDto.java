package com.flexpag.microservicepagamento.model.dto.transaction;

import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;

public record TransactionResponseDto (

    Long id,
     
    PaymentTypeEnum paymentType,

    Long authorizationCode,

    Integer installments,

    Long purchase_id){

    public TransactionResponseDto(Transaction transaction) {
        this(transaction.getId(), transaction.getPaymentType(), transaction.getAuthorizationCode(), 
        transaction.getInstallments(), transaction.getPurchase().getId());
    }

}

