package com.flexpag.microservicepagamento.controller;

import com.flexpag.microservicepagamento.model.dto.api.AddressAPIDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cepapi")
public class ConsultaCepController {

    @GetMapping("/{cep}")
    public AddressAPIDTO consultaCep(@PathVariable String cep){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AddressAPIDTO> response = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json",  cep), AddressAPIDTO.class);
        return response.getBody();
    }
}
