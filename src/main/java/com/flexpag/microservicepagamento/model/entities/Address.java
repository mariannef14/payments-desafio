package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    private String street;
    private String number;
    private String city;
    private String state;
    private String complement;

    public Address(AddressDto addressDto){

        this.street = addressDto.street();
        this.city = addressDto.city();
        this.state = addressDto.state();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
        
    }
}
