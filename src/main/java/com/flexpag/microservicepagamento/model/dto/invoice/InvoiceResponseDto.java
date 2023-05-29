package com.flexpag.microservicepagamento.model.dto.invoice;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.microservicepagamento.model.entities.Invoice;

public record InvoiceResponseDto(

    Long id,

    @JsonFormat(pattern="dd/MM/yyyy")
    LocalDate dueDate,
    
    Long amount,
    
    Boolean paid,
    
    Long contractNumber){

    public InvoiceResponseDto(Invoice invoice) {
        this(invoice.getId(), invoice.getDueDate(), invoice.getAmount(), invoice.getPaid(), invoice.getContractNumber());
    }
}
