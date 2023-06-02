package com.flexpag.microservicepagamento.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseDto;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity{

    private Long amount;

    private Long invoiceAmount;

    private Double rate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "purchase_client", joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "purchase_invoice", joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName = "id"))
    private List<Invoice> invoices;

    public Purchase(PurchaseDto purchaseDto, Client client, List<Invoice> invoices){

        this.setCreateAt(LocalDate.now());
        this.amount = purchaseDto.amount();
        this.invoiceAmount = purchaseDto.invoiceAmount();
        this.rate = purchaseDto.rate();
        this.client = client;
        this.invoices = invoices;
        
    }
}
