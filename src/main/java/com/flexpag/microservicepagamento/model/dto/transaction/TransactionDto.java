package com.flexpag.microservicepagamento.model.dto.transaction;

import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import jakarta.validation.constraints.NotNull;

public record TransactionDto(

        PaymentTypeEnum paymentType,

        @NotNull
        Long authorizationCode,

        @NotNull
        Integer installments,

        @NotNull
        Long purchase_id) {
}
