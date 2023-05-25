package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
