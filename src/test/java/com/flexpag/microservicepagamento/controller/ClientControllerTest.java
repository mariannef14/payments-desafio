package com.flexpag.microservicepagamento.controller;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.flexpag.microservicepagamento.service.ClientService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
//@RequiredArgsConstructor
@ActiveProfiles(value = "test")
public class ClientControllerTest {

    // @Autowired
    // private ClientController clientController;

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "Ao buscar um cliente através do seu id que não está cadastrado, uma exceção deve ser retornada")
    @WithMockUser
    public void shouldReturnNotFoundWhenFindClient() throws Exception{

        when(clientService.consultClient(1L))
            .thenReturn(null);
        
        var response = mockMvc.perform(
            MockMvcRequestBuilders.get("/payments/client")
        .param("id", "1L")
        .contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        //Assertions.assertThrows(EntityNotFoundException.class, () -> clientController.consultClient(25L));
    }
}
