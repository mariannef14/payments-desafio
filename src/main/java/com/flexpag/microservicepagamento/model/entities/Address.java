package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.AddressDTO;
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

    public Address(AddressDTO addressDTO){
        this.street = addressDTO.street();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.complement = addressDTO.complement();
    }
}
