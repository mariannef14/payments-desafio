package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
