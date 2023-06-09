package com.flexpag.microservicepagamento.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DataAuthenticationDto(

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String login,

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String password) {
}
