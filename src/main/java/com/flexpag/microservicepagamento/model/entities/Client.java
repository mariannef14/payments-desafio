package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.ClientDTO;
import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;
import com.flexpag.microservicepagamento.model.interfaces.Assignment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    private Long contractNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Purchase> purchase;

    public Client(ClientDTO clientDTO){
        this.setCreateAt(LocalDate.now());
        this.name = clientDTO.name();
        this.identity = clientDTO.identity();
        this.contract = clientDTO.contract();
        this.email = clientDTO.email();
        this.password = clientDTO.password();
        this.address = new Address(clientDTO.address());
        this.contractNumber = clientDTO.contractNumber();

    }
    @Override
    public List<Double> getFees(ContractTypeEnum contractType) {
        return null;
    }
}
