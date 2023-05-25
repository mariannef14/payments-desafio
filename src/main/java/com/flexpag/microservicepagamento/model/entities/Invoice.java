package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.InvoiceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity{

    private LocalDate dueDate = LocalDate.now();

    private String barcode;

    private Long amount;

    private boolean paid;

    private Long contractNumber;

    public Invoice(InvoiceDTO invoiceDTO){
        this.setCreateAt(LocalDate.now());
        this.dueDate = invoiceDTO.dueDate();
        this.barcode = invoiceDTO.barcode();
        this.amount = invoiceDTO.amount();
        this.paid = invoiceDTO.paid();
        this.contractNumber = invoiceDTO.contractNumber();
    }
}
