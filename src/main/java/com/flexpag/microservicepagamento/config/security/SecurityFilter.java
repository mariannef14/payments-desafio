package com.flexpag.microservicepagamento.config.security;


import com.flexpag.microservicepagamento.model.repository.UserPaymentsRepository;
import com.flexpag.microservicepagamento.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserPaymentsRepository userPaymentsRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recoverToken(request);

        if(tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails user = userPaymentsRepository.findByLogin(subject);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
