package com.flexpag.microservicepagamento.model.dto;

import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;

public record TransactionDTO(

        PaymentTypeEnum paymentType,

        Long authorizationCode,

        Long purchase_id) {
}
