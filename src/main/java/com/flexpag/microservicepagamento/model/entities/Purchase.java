package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.PurchaseDTO;
import com.flexpag.microservicepagamento.model.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity{

    private Long amount;

    private Long invoiceAmount;

    private Double rate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "purchase_client", joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "purchase_invoice", joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName = "id"))
    private Set<Invoice> invoices;

    public Purchase(PurchaseDTO purchaseDTO, Client client){
        this.setCreateAt(LocalDate.now());
        this.amount = purchaseDTO.amount();
        this.invoiceAmount = purchaseDTO.invoiceAmount();
        this.rate = purchaseDTO.rate();
        this.client = client;
    }
}
