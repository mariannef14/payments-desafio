package com.flexpag.microservicepagamento.model.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.flexpag.microservicepagamento.model.dto.user.DataAuthenticationDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPayments extends BaseEntity implements UserDetails{

    private String login;
    private String password;


    // public UserPayments(DataAuthenticationDto dataAuthenticationDto){
    //     this.setCreateAt(LocalDate.now());
    //     this.login = dataAuthenticationDto.login();
    //     this.password = passwordCryptografado;
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;   
    }

    @Override
    public boolean isAccountNonLocked() {        
        return true;   
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
