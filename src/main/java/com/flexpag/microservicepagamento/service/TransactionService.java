package com.flexpag.microservicepagamento.service;

import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.model.repository.PurchaseRepository;
import com.flexpag.microservicepagamento.model.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final PurchaseRepository purchaseRepository;
    private final TransactionRepository transactionRepository;
    
    public TransactionResponseDto saveTransaction(TransactionDto transactionDto){
        Purchase purchase = purchaseRepository.findById(transactionDto.purchase_id())
        .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));

        Transaction transaction = transactionRepository.save(new Transaction(transactionDto, purchase));
        return new TransactionResponseDto(transaction);
    }
}
