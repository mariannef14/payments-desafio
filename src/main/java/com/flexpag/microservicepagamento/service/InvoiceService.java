package com.flexpag.microservicepagamento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceDto;
import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceResponseDto;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.entities.Invoice;
import com.flexpag.microservicepagamento.model.repository.ClientRepository;
import com.flexpag.microservicepagamento.model.repository.InvoiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;


    public InvoiceResponseDto saveInvoice(InvoiceDto invoiceDto){
        Invoice invoice = invoiceRepository.save(new Invoice(invoiceDto));
        return new InvoiceResponseDto(invoice);
    }

    public List<InvoiceResponseDto> consultInvoice(Long id){        
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        List<Invoice> invoices =  invoiceRepository.findAllByContractNumberAndPaidFalse(client.getContractNumber());
        
        return invoices.stream().map(InvoiceResponseDto::new).toList();
    }

    public List<Invoice> updateInvoices(List<Invoice> invoices) {

        invoices.forEach(invoice -> invoice.setPaid(true));

        return invoices;
    }
}
