package com.flexpag.microservicepagamento.controller;

import com.flexpag.microservicepagamento.model.dto.security.TokenJWTDto;
import com.flexpag.microservicepagamento.model.dto.user.DataAuthenticationDto;
import com.flexpag.microservicepagamento.model.entities.UserPayments;
import com.flexpag.microservicepagamento.service.UserPaymentsService;
import com.flexpag.microservicepagamento.service.security.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    private final UserPaymentsService userPaymentsService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DataAuthenticationDto dadosAuthenticationDto){
        Authentication token = new UsernamePasswordAuthenticationToken(dadosAuthenticationDto.login(), dadosAuthenticationDto.password());
        Authentication authentication = authenticationManager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((UserPayments)  authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    }

    @PostMapping("/cadastro/usuario")
    public ResponseEntity<DataAuthenticationDto> saveUser(@RequestBody @Valid DataAuthenticationDto dataAuthenticationDto){

        DataAuthenticationDto userPayments = userPaymentsService.saveUser(dataAuthenticationDto);

        return ResponseEntity.ok(userPayments);
    }
}
