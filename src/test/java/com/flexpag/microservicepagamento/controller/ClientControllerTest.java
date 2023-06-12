package com.flexpag.microservicepagamento.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.flexpag.microservicepagamento.model.dto.AddressDto;
import com.flexpag.microservicepagamento.model.dto.client.ClientDto;
import com.flexpag.microservicepagamento.model.dto.client.ClientResponseDto;
import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;
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

import com.flexpag.microservicepagamento.service.ClientService;

import java.net.URI;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles(value = "test")
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ClientDto> clientDtoJson;

    @Autowired
    private JacksonTester<ClientResponseDto> clientResponseDtoJson;


    @Test
    @DisplayName(value = "Ao enviar informações válidas do cliente, deve ser retornado um status 200")
    public void shouldReturnOkWhenSaveClient() throws Exception{

        when(clientService.saveClient(any())).thenReturn(new ClientResponseDto(null, "Marianne","123456789",
                "marianne@gmail.com", "teste",
                new AddressDto("Av Um", "Jaboatao", "Pernambuco", "402", ""),
                30L, null ));



        URI uri = new URI("/payments/client/");

        ClientDto clientDto =  new ClientDto("Marianne", "123456789",
                ContractTypeEnum.PF, "marianne@gmail.com", "teste",
                new AddressDto("Av Um", "Jaboatão", "Pernambuco", "402", "")
                , 30L);


        var response = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDtoJson.write(clientDto).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


        ClientResponseDto clientResponseDto =  new ClientResponseDto(null, "Marianne","123456789",
                "marianne@gmail.com", "teste",
                new AddressDto("Av Um", "Jaboatao", "Pernambuco", "402", ""),
                30L, null );

        String jsonEsperado = clientResponseDtoJson.write(clientResponseDto).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    @Test
    @DisplayName(value = "Ao buscar um cliente, que não está cadastrado, através do seu id, deve retornado o status 404")
    @WithMockUser
    public void shouldReturnNotFoundWhenFindClient() throws Exception{

        URI uri = new URI("/payments/client/");

        when(clientService.consultClient(anyLong())).thenReturn(null);


        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .param("id", "32L"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    @DisplayName(value = "Ao buscar um cliente, que não está cadastrado, através do seu id, uma exceção deve ser lançada")
    @WithMockUser
    public void shouldReturnExceptionWhenFindClient() throws Exception{

        URI uri = new URI("/payments/client/");

        when(clientService.consultClient(anyLong()))
                .thenThrow(EntityNotFoundException.class);


        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .param("id", "32L"))
                                .andReturn().getResponse();

        assertThrows(EntityNotFoundException.class, () -> clientService.consultClient(32L));
    }

    @Test
    @DisplayName("Ao buscar um cliente cadastrado no banco, um status 200 deve ser retornado")
    @WithMockUser
    public void shoudReturnOkWhenFindClient() throws Exception {

        URI uri = new URI("/payments/client/");

        when(clientService.consultClient(anyLong())).thenReturn(new ClientResponseDto(28L, "Marianne","123456789",
                "marianne@gmail.com", "teste",
                new AddressDto("Av Um", "Jaboatao", "Pernambuco", "402", ""),
                30L, null ));


        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .param("id", "28L"))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
