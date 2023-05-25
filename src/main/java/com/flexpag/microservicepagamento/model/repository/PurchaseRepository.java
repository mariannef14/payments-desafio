package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
