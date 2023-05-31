package com.flexpag.microservicepagamento.service;

import java.util.List;

import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.repository.PurchaseRepository;
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
    private final PurchaseRepository purchaseRepository;


    public InvoiceResponseDto saveInvoice(InvoiceDto invoiceDto){
        Invoice invoice = invoiceRepository.save(new Invoice(invoiceDto));
        return new InvoiceResponseDto(invoice);
    }

    public List<InvoiceResponseDto> consultInvoice(Long id){        
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        List<Invoice> invoices =  invoiceRepository.findAllByContractNumberAndPaidFalse(client.getContractNumber());
        
        return invoices.stream().map((invoice) -> new InvoiceResponseDto(invoice)).toList();
    }

    public List<Invoice> updateInvoices(List<Invoice> invoices) {

        invoices.forEach(invoice -> invoice.setPaid(true));

        System.out.println(invoices);

        return invoices;

    }
}
