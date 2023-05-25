package com.flexpag.microservicepagamento.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank
        String street,

        @NotBlank
        String number,

        @NotBlank
        String city,

        @NotBlank
        String state,

        String complement) {
}
