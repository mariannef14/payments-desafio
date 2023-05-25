package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
