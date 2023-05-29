package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Random;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PENDING;

    private Long authorizationCode;

    private Integer installments;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Purchase purchase;

    public Transaction(TransactionDto transactionDto, Purchase purchase){

        this.setCreateAt(LocalDate.now());
        this.paymentType = transactionDto.paymentType();
        // Random random = new Random();
        // Integer pos = random.nextInt(0, 3);
        // this.status = StatusEnum.getPos(pos);//lógica randômica
        this.authorizationCode = transactionDto.authorizationCode();
        this.installments = transactionDto.installments();
        this.purchase = purchase;
        
    }
}
