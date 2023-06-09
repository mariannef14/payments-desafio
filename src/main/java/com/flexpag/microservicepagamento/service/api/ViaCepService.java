package com.flexpag.microservicepagamento.service.api;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.model.dto.api.AddressAPIDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    public AddressDto consultCep(String cep) {

        if(!isValid(cep)){
            throw new IllegalArgumentException("Cep inválido");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AddressAPIDto> response = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json", cep), AddressAPIDto.class);
        return new AddressDto(response.getBody().logradouro(), response.getBody().localidade(),
                response.getBody().uf(), "", response.getBody().complemento());
    }

    public boolean isValid(String cep){
        return cep.matches("\\d{8}");
    }
}
