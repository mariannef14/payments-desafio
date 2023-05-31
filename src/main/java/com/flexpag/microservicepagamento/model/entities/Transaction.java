package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Random;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Long authorizationCode;

    private Integer installments;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Purchase purchase;

    public Transaction(TransactionDto transactionDto, Purchase purchase, StatusEnum status){

        this.setCreateAt(LocalDate.now());
        this.paymentType = transactionDto.paymentType();
        this.status = status;
        this.authorizationCode = transactionDto.authorizationCode();
        this.installments = transactionDto.installments();
        this.purchase = purchase;
        
    }

    public static StatusEnum getStatusEnum(){
        StatusEnum[] values = StatusEnum.values();
        int status = new Random().nextInt(StatusEnum.values().length);
        return values[status];
    }
}
