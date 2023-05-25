package com.flexpag.microservicepagamento.model.entities;

import com.flexpag.microservicepagamento.model.dto.TransactionDTO;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Long authorizationCode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Purchase purchase;

    public Transaction(TransactionDTO transactionDTO, Purchase purchase){
        this.setCreateAt(LocalDate.now());
        this.paymentType = transactionDTO.paymentType();
        this.status = null;//lógica randômica
        this.authorizationCode = transactionDTO.authorizationCode();
        this.purchase = purchase;
    }
}
