package com.flexpag.microservicepagamento.service;

import com.flexpag.microservicepagamento.model.dto.ClientDTO;
import com.flexpag.microservicepagamento.model.dto.InvoiceDTO;
import com.flexpag.microservicepagamento.model.dto.PurchaseDTO;
import com.flexpag.microservicepagamento.model.dto.TransactionDTO;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.entities.Invoice;
import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.model.repository.ClientRepository;
import com.flexpag.microservicepagamento.model.repository.InvoiceRepository;
import com.flexpag.microservicepagamento.model.repository.PurchaseRepository;
import com.flexpag.microservicepagamento.model.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final PurchaseRepository purchaseRepository;
    private final TransactionRepository transactionRepository;

    public Invoice cadastrarInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice = invoiceRepository.save(new Invoice(invoiceDTO));
        return invoice;
    }

    public Client cadastrarClient(ClientDTO clientDTO){
        Client client = clientRepository.save(new Client(clientDTO));
        return client;
    }

    public Purchase cadastrarPurchase(PurchaseDTO purchaseDTO){
        Client client = clientRepository.findById(purchaseDTO.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

        /*Transaction transaction = transactionRepository.findById(purchaseDTO.transaction_id())
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));*/

        Purchase purchase = purchaseRepository.save(new Purchase(purchaseDTO, client));

        return purchase;
    }

    public Transaction cadastrarTransaction(TransactionDTO transactionDTO){
        Purchase purchase = purchaseRepository.findById(transactionDTO.purchase_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

        Transaction transaction = transactionRepository.save(new Transaction(transactionDTO, purchase));
        return transaction;
    }
}
