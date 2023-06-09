package com.flexpag.microservicepagamento.model.dto.transaction;

import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import jakarta.validation.constraints.NotNull;

public record TransactionDto(

        @NotNull(message = "Este campo não pode ser nulo")
        PaymentTypeEnum paymentType,

        @NotNull(message = "Este campo não pode ser nulo")
        Long authorizationCode,

        @NotNull(message = "Este campo não pode ser nulo")
        Integer installments,

        @NotNull(message = "Este campo não pode ser nulo")
        Long purchase_id) {
}
