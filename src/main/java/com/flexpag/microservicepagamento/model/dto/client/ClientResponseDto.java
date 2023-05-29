package com.flexpag.microservicepagamento.model.dto.client;

import java.util.List;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.model.entities.Client;

public record ClientResponseDto(

    Long id, 

    String name, 
        
    String identity, 
    
    String email,
    
    String password,
    
    AddressDto address,

    Long contractNumber,
    
    List<Long> purchases_id){

    public ClientResponseDto(Client client){
        this(client.getId(), client.getName(), client.getIdentity(), client.getEmail(), 
        client.getPassword(), new AddressDto(client.getAddress()), 
        client.getContractNumber(), client.getPurchase().stream().map((purchase) -> purchase.getId()).toList());
    }
}
