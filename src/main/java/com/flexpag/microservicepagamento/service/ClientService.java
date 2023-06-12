package com.flexpag.microservicepagamento.service;

import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.client.ClientDto;
import com.flexpag.microservicepagamento.model.dto.client.ClientResponseDto;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.entities.UserPayments;
import com.flexpag.microservicepagamento.model.repository.ClientRepository;
import com.flexpag.microservicepagamento.model.repository.UserPaymentsRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClientService {
    
    private final ClientRepository clientRepository; 
    private final UserPaymentsRepository userPaymentsRepository;

    private final PasswordEncoder passwordEncoder;

    public ClientResponseDto saveClient(ClientDto clientDTO){

        if(clientRepository.existsByEmail(clientDTO.email())){
            throw new EntityExistsException("Esse email já foi cadastrado no sistema");
        }
        if(clientRepository.existsByIdentity(clientDTO.identity())){
            throw new EntityExistsException("Essa identidade já está cadastrada no sistema");
        }
        if(clientRepository.existsByContractNumber(clientDTO.contractNumber())){
            throw new EntityExistsException("Esse número de contrato já está cadastrado no sistema");
        }

        Client client = clientRepository.save(new Client(clientDTO));

        String password = passwordEncoder.encode(clientDTO.password());
        userPaymentsRepository.save(new UserPayments(clientDTO.email(), password));

        return new ClientResponseDto(client);
    }

    public ClientResponseDto consultClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        return new ClientResponseDto(client);
    }
}
