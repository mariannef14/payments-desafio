package com.flexpag.microservicepagamento.controller;

import java.net.URI;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseDto;
import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseResponseDto;
import com.flexpag.microservicepagamento.service.PurchaseService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments/purchase")
public class PurchaseController {
    
    private final PurchaseService purchaseService;


    @PostMapping("/")
    @Transactional
    public ResponseEntity<PurchaseResponseDto> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        
        PurchaseResponseDto purchase = purchaseService.savePurchase(purchaseDto);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(purchase.id()).toUri();
        return ResponseEntity.created(uri).body(purchase);
    }
}
