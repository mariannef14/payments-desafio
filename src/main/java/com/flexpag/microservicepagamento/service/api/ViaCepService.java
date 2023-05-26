package com.flexpag.microservicepagamento.service.api;

import com.flexpag.microservicepagamento.model.dto.AddressDTO;
import com.flexpag.microservicepagamento.model.dto.api.AddressAPIDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    public AddressDTO consultaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AddressAPIDTO> response = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json", cep), AddressAPIDTO.class);
        return new AddressDTO(response.getBody().logradouro(), response.getBody().localidade(),
                response.getBody().uf(), response.getBody().complemento());
    }
}
