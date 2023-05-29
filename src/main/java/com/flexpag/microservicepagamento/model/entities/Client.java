package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.client.ClientDto;
import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;
import com.flexpag.microservicepagamento.model.interfaces.Assignment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseEntity implements Assignment {

    private  String name;

    private  String identity;

    @Enumerated(EnumType.STRING)
    private ContractTypeEnum contract;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    private Long contractNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Purchase> purchase;

    public Client(ClientDto clientDto){

        this.setCreateAt(LocalDate.now());
        this.name = clientDto.name();
        this.identity = clientDto.identity();
        this.contract = clientDto.contract();
        this.email = clientDto.email();
        this.password = clientDto.password();
        this.address = new Address(clientDto.address());
        this.contractNumber = clientDto.contractNumber();
        this.purchase = new HashSet<>();

    }

    @Override
    public List<Double> getFees(ContractTypeEnum contractType) {
        return null;
    }
}
