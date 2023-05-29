package com.flexpag.microservicepagamento.model.dto.transaction;

import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;

public record TransactionResponseDto (

    Long id,
     
    PaymentTypeEnum paymentType,

    Long authorizationCode,

    StatusEnum status,

    Integer installments,

    Long purchase_id){

    public TransactionResponseDto(Transaction transaction) {
        this(transaction.getId(), transaction.getPaymentType(), transaction.getAuthorizationCode(),
                transaction.getStatus(), transaction.getInstallments(), transaction.getPurchase().getId());
    }

}

