package com.flexpag.microservicepagamento.model.dto.transaction;

import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;

public record TransactionDto(

        PaymentTypeEnum paymentType,

        Long authorizationCode,

        Integer installments,

        Long purchase_id) {
}
