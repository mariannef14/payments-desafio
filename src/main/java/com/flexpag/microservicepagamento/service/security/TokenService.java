package com.flexpag.microservicepagamento.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.flexpag.microservicepagamento.model.entities.UserPayments;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserPayments user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Payments")
                .withSubject(user.getLogin())
                .withClaim("id", user.getId())
                .withExpiresAt(dateExpiration())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerrar token jwt", exception);
        }        
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Payments")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
    private Instant dateExpiration() {
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
    }
}