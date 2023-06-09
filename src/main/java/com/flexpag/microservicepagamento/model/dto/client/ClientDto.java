package com.flexpag.microservicepagamento.model.dto.client;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDto(

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String name,

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String identity,

        @NotNull(message = "Este campo não pode ser nulo")
        ContractTypeEnum contract,

        @Email(message = "Email inválido")
        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String email,

        @NotBlank(message = "Este campo não pode ser vazio ou nulo")
        String password,

        @NotNull(message = "Este campo não pode ser nulo")
        @Valid
        AddressDto address,

        @NotNull(message = "Este campo não pode ser nulo")
        Long contractNumber) {
}
