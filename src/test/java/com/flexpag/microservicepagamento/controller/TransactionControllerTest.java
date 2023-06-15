package com.flexpag.microservicepagamento.controller;

import com.flexpag.microservicepagamento.model.dto.transaction.TransactionDto;
import com.flexpag.microservicepagamento.model.dto.transaction.TransactionResponseDto;
import com.flexpag.microservicepagamento.model.enums.PaymentTypeEnum;
import com.flexpag.microservicepagamento.model.enums.StatusEnum;
import com.flexpag.microservicepagamento.service.TransactionService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TransactionDto> transactionDtoJson;

    @Autowired
    private JacksonTester<TransactionResponseDto> transactionResponseDtoJson;


    @Test
    @DisplayName("Ao enviar informações válidas de uma transaction, deve ser retornado um status 201")
    @WithMockUser
    void shouldReturnOkWhenSaveTransaction() throws Exception{

        when(transactionService.saveTransaction(any())).thenReturn(
                new TransactionResponseDto(15L, PaymentTypeEnum.PIX, 123456789L,
                        StatusEnum.AUTHORIZED, 2, 12L));


        URI uri = new URI("/payments/transaction/");


        TransactionDto transactionDto = new TransactionDto(PaymentTypeEnum.PIX, 123456789L, 2, 12L);

        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionDtoJson.write(transactionDto).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(15L, PaymentTypeEnum.PIX, 123456789L,
                StatusEnum.AUTHORIZED, 2, 12L);

        String jsonEsperado = transactionResponseDtoJson.write(transactionResponseDto).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Ao tentar persistir uma purchase com id de cliente que não existe, uma exceção deve ser lançada")
    @WithMockUser
    void shouldReturnExceptionWhenSaveTransactionWithPurchaseNotExist() throws Exception{

        when(transactionService.saveTransaction(any())).thenThrow(EntityNotFoundException.class);

        URI uri = new URI("/payments/transaction/");

        TransactionDto transactionDto = new TransactionDto(PaymentTypeEnum.PIX, 123456789L, 2, 12L);

        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDtoJson.write(transactionDto).getJson()))
                .andReturn().getResponse();


        assertThrows(EntityNotFoundException.class, () -> transactionService.saveTransaction(transactionDto));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
