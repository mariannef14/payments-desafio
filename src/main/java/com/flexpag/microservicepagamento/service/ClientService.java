package com.flexpag.microservicepagamento.service;

import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.client.ClientDto;
import com.flexpag.microservicepagamento.model.dto.client.ClientResponseDto;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    
    private final ClientRepository clientRepository; 

    public ClientResponseDto saveClient(ClientDto clientDTO){
        Client client = clientRepository.save(new Client(clientDTO));
        return new ClientResponseDto(client);
    }

    public ClientResponseDto consultClient(Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não existe"));
        return new ClientResponseDto(client);
    }
}
