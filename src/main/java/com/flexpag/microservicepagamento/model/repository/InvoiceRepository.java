package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByContractNumberAndPaidFalse(Long contractNumber);

    //List<Invoice> findAllByPurchase(Long id);
}
