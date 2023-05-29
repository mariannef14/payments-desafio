package com.flexpag.microservicepagamento.model.dto.api;

public record AddressAPIDto(
        String cep,

        String logradouro,

        String complemento,

        String bairro,

        String localidade,

        String uf,

        String ibge,

        String gia,

        String ddd,
        
        String siafi){}
