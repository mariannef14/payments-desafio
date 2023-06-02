package com.flexpag.microservicepagamento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseDto;
import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseResponseDto;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.entities.Invoice;
import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.repository.ClientRepository;
import com.flexpag.microservicepagamento.model.repository.InvoiceRepository;
import com.flexpag.microservicepagamento.model.repository.PurchaseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    
    private final ClientRepository clientRepository;
    private final InvoiceRepository invoiceRepository;
    private final PurchaseRepository purchaseRepository;
    

    public PurchaseResponseDto savePurchase(PurchaseDto purchaseDto){
        Client client = clientRepository.findById(purchaseDto.clientId())
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        List<Invoice> invoices = invoiceRepository.findAllById(purchaseDto.invoices_id());

        Purchase purchase = purchaseRepository.save(new Purchase(purchaseDto, client, invoices));

        return new PurchaseResponseDto(purchase);
    }
}
