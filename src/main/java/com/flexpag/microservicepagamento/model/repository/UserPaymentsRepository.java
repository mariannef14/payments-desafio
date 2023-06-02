package com.flexpag.microservicepagamento.model.repository;

import com.flexpag.microservicepagamento.model.entities.UserPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserPaymentsRepository extends JpaRepository<UserPayments, Long> {
    UserDetails findByLogin(String login);
}
