package com.flexpag.microservicepagamento.controller;

import java.net.URI;

import com.flexpag.microservicepagamento.model.entities.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import com.flexpag.microservicepagamento.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments/transaction")
public class TransactionalController {
    
    private final TransactionService transactionService;


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<TransactionResponseDto> saveTransaction(@RequestBody @Valid TransactionDto transactionDto,
                                                            UriComponentsBuilder uriComponentsBuilder){

        TransactionResponseDto transaction = transactionService.saveTransaction(transactionDto);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(transaction.id()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }

}
