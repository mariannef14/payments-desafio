package com.flexpag.microservicepagamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.client.ClientDto;
import com.flexpag.microservicepagamento.model.dto.client.ClientResponseDto;
import com.flexpag.microservicepagamento.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("payments/client")
public class ClientController {
   
    private final ClientService clientService;

    @PostMapping(value = "/")
    @Transactional
    public ResponseEntity<ClientResponseDto> saveClient(@RequestBody @Valid ClientDto clientDto,
                                                  UriComponentsBuilder uriComponentsBuilder) {
                                                    
        ClientResponseDto client = clientService.saveClient(clientDto);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(client.id()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping("/")
    public ResponseEntity<ClientResponseDto> consultClient(@RequestParam Long id){
        ClientResponseDto clientResponseDto = clientService.consultClient(id);
        return ResponseEntity.ok(clientResponseDto);
    } 
    
}
