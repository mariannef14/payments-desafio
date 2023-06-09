package com.flexpag.microservicepagamento.controller.api;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.service.api.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cepapi")
public class ConsultCepController {

    private final ViaCepService viaCepService;
    @GetMapping("/{cep}")
    public AddressDto consultCep(@PathVariable String cep){
        return viaCepService.consultCep(cep);
    }
}
