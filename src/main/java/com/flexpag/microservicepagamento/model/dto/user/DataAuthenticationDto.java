package com.flexpag.microservicepagamento.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DataAuthenticationDto(

        @NotBlank
        String login,

        @NotBlank
        String password) {
}
