package com.flexpag.microservicepagamento.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    private String login;
    private String username;
    private String password;
}
