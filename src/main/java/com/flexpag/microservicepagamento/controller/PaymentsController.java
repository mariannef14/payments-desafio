package com.flexpag.microservicepagamento.controller;

import com.flexpag.microservicepagamento.model.dto.ClientDTO;
import com.flexpag.microservicepagamento.model.dto.InvoiceDTO;
import com.flexpag.microservicepagamento.model.dto.PurchaseDTO;
import com.flexpag.microservicepagamento.model.dto.TransactionDTO;
import com.flexpag.microservicepagamento.model.entities.Client;
import com.flexpag.microservicepagamento.model.entities.Invoice;
import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @PostMapping("/invoice/cadastro")
    public ResponseEntity<Invoice> cadastrarInvoice(@RequestBody InvoiceDTO invoiceDTO,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        Invoice invoice = paymentsService.cadastrarInvoice(invoiceDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).body(invoice);
    }

    @PostMapping("/client/cadastro")
    public ResponseEntity<Client> cadastrarClient(@RequestBody ClientDTO clientDTO,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        Client client = paymentsService.cadastrarClient(clientDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @PostMapping("purchase/cadastro")
    public ResponseEntity<Purchase> cadastrarPurchase(@RequestBody PurchaseDTO purchaseDTO,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        Purchase purchase = paymentsService.cadastrarPurchase(purchaseDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(purchase.getId()).toUri();
        return ResponseEntity.created(uri).body(purchase);
    }

    @PostMapping("/transaction/cadastro")
    public ResponseEntity<Transaction> cadastrarTransaction(@RequestBody TransactionDTO transactionDTO,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Transaction transaction = paymentsService.cadastrarTransaction(transactionDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }
}
