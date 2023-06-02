package com.flexpag.microservicepagamento.model.dto;

import com.flexpag.microservicepagamento.model.entities.Address;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(

        @NotBlank
        String street,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String number,

        String complement) {

        public AddressDto(Address address) {
                this(address.getStreet(), address.getCity(), address.getState(), address.getNumber(), address.getComplement());
        }
}
