package com.flexpag.microservicepagamento.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceDto;
import com.flexpag.microservicepagamento.model.dto.invoice.InvoiceResponseDto;
import com.flexpag.microservicepagamento.service.InvoiceService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles(value = "test")
public class InvoiceControllerTest {

    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<InvoiceDto> invoiceDtoJson;

    @Autowired
    private JacksonTester<InvoiceResponseDto> invoiceResponseDtoJson;


    @Test
    @DisplayName("Ao enviar informações válidas do cliente, deve ser retornado um status 200")
    @WithMockUser
    public void shouldReturnOkWhenSaveInvoice()throws Exception{

        when(invoiceService.saveInvoice(any())).thenReturn(new InvoiceResponseDto(null, LocalDate.now().plusDays(5),
                100L, false, 30L));


        URI uri = new URI("/payments/invoice/");

        InvoiceDto invoiceDto = new InvoiceDto(LocalDate.now().plusDays(1), "qp7gbtds4a3",
                100L, false, 30L );

        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceDtoJson.write(invoiceDto).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto(null, LocalDate.now().plusDays(5),
                100L, false, 30L);

        String jsonEsperado = invoiceResponseDtoJson.write(invoiceResponseDto).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    @Test
    @DisplayName(value = "Ao buscar um cliente, que não está cadastrado, através do seu id, uma exceção deve ser lançada")
    @WithMockUser
    public void shouldReturnExceptionWhenFindClient() throws Exception {

        URI uri = new URI("/payments/invoice/");

        when(invoiceService.consultInvoice(anyLong())).thenThrow(EntityNotFoundException.class);

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .param("id", "20"))
                .andReturn().getResponse();

        assertThrows(EntityNotFoundException.class, () -> invoiceService.consultInvoice(20L));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName(value = "Ao buscar um cliente, que não está cadastrado, através do seu id, uma exceção deve ser lançada")
    @WithMockUser
    public void shouldReturnOkWhenFindInvoiceByClient() throws Exception {

        URI uri = new URI("/payments/invoice/");
        List<InvoiceResponseDto> invoices = new ArrayList<>();
        invoices.add(new InvoiceResponseDto(null, LocalDate.now().plusDays(5),
                100L, false, 30L));

        when(invoiceService.consultInvoice(anyLong())).thenReturn(invoices);

        var response = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .param("id", "28"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
