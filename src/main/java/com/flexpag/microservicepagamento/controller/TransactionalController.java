package com.flexpag.microservicepagamento.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.service.TransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments/client/transactional")
public class TransactionalController {
    
    private final TransactionService transactionService;


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<TransactionResponseDto> cadastrarTransaction(@RequestBody TransactionDto transactionDTO,
                                                            UriComponentsBuilder uriComponentsBuilder){
        
        TransactionResponseDto transaction = transactionService.saveTransaction(transactionDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(transaction.id()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }
}
