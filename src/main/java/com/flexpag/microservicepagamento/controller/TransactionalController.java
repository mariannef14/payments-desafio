package com.flexpag.microservicepagamento.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import com.flexpag.microservicepagamento.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments/purchase/transactional")
public class TransactionalController {
    
    private final TransactionService transactionService;


    @PostMapping("/cadastro")
    //@Transactional
    public ResponseEntity<TransactionResponseDto> cadastrarTransaction(@RequestBody TransactionDto transactionDTO,
                                                            UriComponentsBuilder uriComponentsBuilder){
        
        TransactionResponseDto transaction = transactionService.saveTransaction(transactionDTO);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(transaction.id()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }

   @GetMapping("/")
   public ResponseEntity<StatusEnum> consultTransaction(@PathVariable Long id){
       StatusEnum transaction = transactionService.consultTransaction(id);
       return ResponseEntity.ok(transaction);
   }
}
