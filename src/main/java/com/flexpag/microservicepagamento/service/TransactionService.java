package com.flexpag.microservicepagamento.service;

import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import org.springframework.stereotype.Service;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.model.entities.Purchase;
import com.flexpag.microservicepagamento.model.entities.Transaction;
import com.flexpag.microservicepagamento.model.repository.PurchaseRepository;
import com.flexpag.microservicepagamento.model.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {
    
    private final PurchaseRepository purchaseRepository;
    private final TransactionRepository transactionRepository;

    private final InvoiceService invoiceService;
    
    public TransactionResponseDto saveTransaction(TransactionDto transactionDto){
        Purchase purchase = purchaseRepository.findById(transactionDto.purchase_id())
        .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));

       StatusEnum status = Transaction.getStatusEnum();

       if(status.equals(StatusEnum.AUTHORIZED)){
            invoiceService.updateInvoices(purchase.getInvoices());
       }

        Transaction transaction = transactionRepository.save(new Transaction(transactionDto, purchase, status));

        return new TransactionResponseDto(transaction);
    }

}
