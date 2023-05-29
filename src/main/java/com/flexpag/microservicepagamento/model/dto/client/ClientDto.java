package com.flexpag.microservicepagamento.model.dto.client;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDto(

        @NotBlank(message = "O campo não pode estar vazio")
        String name,

        @NotBlank(message = "O campo não pode estar vazio")
        String identity,

        ContractTypeEnum contract,

        @Email(message = "Email inválido")
        @NotBlank(message = "O campo não pode estar vazio")
        String email,

        @NotBlank(message = "O campo não pode estar vazio")
        String password,

        @NotNull(message = "O campo não pode estar vazio")
        @Valid
        AddressDto address,
        
        Long contractNumber) {
}
