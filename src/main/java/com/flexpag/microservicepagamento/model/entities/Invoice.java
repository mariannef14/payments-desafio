package com.flexpag.microservicepagamento.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceDto;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity{

    private LocalDate dueDate;

    @Column(unique = true)
    private String barcode;

    private Long amount;

    @Setter
    private Boolean paid;

    private Long contractNumber;
   
    
    public Invoice(InvoiceDto invoiceDto){

        this.setCreateAt(LocalDate.now());
        this.dueDate = invoiceDto.dueDate();
        this.barcode = invoiceDto.barcode();
        this.amount = invoiceDto.amount();
        this.paid = invoiceDto.paid();
        this.contractNumber = invoiceDto.contractNumber();
        
    }
}
