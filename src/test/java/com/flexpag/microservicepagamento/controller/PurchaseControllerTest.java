package com.flexpag.microservicepagamento.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseDto;
import com.flexpag.microservicepagamento.model.dto.purchase.PurchaseResponseDto;
import com.flexpag.microservicepagamento.service.PurchaseService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles(value = "test")
public class PurchaseControllerTest {

    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PurchaseDto> purchaseDtoJson;

    @Autowired
    private JacksonTester<PurchaseResponseDto> purchaseResponseDtoJson;

    @Test
    @DisplayName("Ao enviar informações válidas de uma purchase, deve ser retornado um status 201")
    @WithMockUser
    void shouldReturnOkWhenSavePurchase() throws Exception{

        List<Long> invoices = new ArrayList<>();
        invoices.add(15L);

        when(purchaseService.savePurchase(any())).thenReturn(
                new PurchaseResponseDto(30L, 100L,  150L, 0.2, 28L, invoices
                ));


        URI uri = new URI("/payments/purchase/");

        PurchaseDto purchaseDto = new PurchaseDto(100L, 150L, 0.2, 28L, invoices);

        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(purchaseDtoJson.write(purchaseDto).getJson()))
                    .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto(30L, 100L,  150L, 0.2, 28L, invoices);

        String jsonEsperado = purchaseResponseDtoJson.write(purchaseResponseDto).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    @Test
    @DisplayName("Ao tentar persistir uma purchase com id de cliente que não existe, uma exceção deve ser lançada")
    @WithMockUser
    void shouldReturnExceptionWhenSavePurchaseWithClientNotExist() throws Exception{

        List<Long> invoices = new ArrayList<>();
        invoices.add(15L);

        when(purchaseService.savePurchase(any())).thenThrow(EntityNotFoundException.class);

        URI uri = new URI("/payments/purchase/");


        PurchaseDto purchaseDto = new PurchaseDto(100L, 150L, 0.2, 28L, invoices);

        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(purchaseDtoJson.write(purchaseDto).getJson()))
                .andReturn().getResponse();

        assertThrows(EntityNotFoundException.class, () -> purchaseService.savePurchase(purchaseDto));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
