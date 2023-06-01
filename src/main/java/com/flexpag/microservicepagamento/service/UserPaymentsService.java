package com.flexpag.microservicepagamento.service;

import com.flexpag.microservicepagamento.model.dto.user.DataAuthenticationDto;
import com.flexpag.microservicepagamento.model.entities.UserPayments;
import com.flexpag.microservicepagamento.model.repository.UserPaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPaymentsService {

    private final UserPaymentsRepository userPaymentsRepository;
    private final PasswordEncoder passwordEncoder;

    public DataAuthenticationDto saveUser(DataAuthenticationDto dataAuthenticationDto){
        String password = passwordEncoder.encode(dataAuthenticationDto.password());
        userPaymentsRepository.save(new UserPayments(dataAuthenticationDto.login(), password));
        return dataAuthenticationDto;
    }
}
