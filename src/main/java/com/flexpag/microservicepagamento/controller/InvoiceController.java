package com.flexpag.microservicepagamento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceDto;
import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceResponseDto;
import com.flexpag.microservicepagamento.service.InvoiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments/client/invoice")
public class InvoiceController {
    
    private final InvoiceService invoiceService;

    
    @PostMapping(value = "/cadastro")
    @Transactional
    public ResponseEntity<InvoiceResponseDto> saveInvoice(@RequestBody @Valid InvoiceDto invoiceDto,
                                                    UriComponentsBuilder uriComponentsBuilder) {

        InvoiceResponseDto invoice = invoiceService.saveInvoice(invoiceDto);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(invoice.id()).toUri();
        return ResponseEntity.created(uri).body(invoice);
    }

    @GetMapping("/")
    public ResponseEntity<List<InvoiceResponseDto>> consultInvoice(@RequestParam Long id) {
       List<InvoiceResponseDto> invoice = invoiceService.consultInvoice(id);
       return ResponseEntity.ok(invoice);
    }
}