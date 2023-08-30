package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateCustomerBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.CustomerBuilder;
import com.architecture.office.management.architecture_office_management.dtos.CustomerList;
import com.architecture.office.management.architecture_office_management.dtos.ErrorData;
import com.architecture.office.management.architecture_office_management.models.Customer;
import com.architecture.office.management.architecture_office_management.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void afterEach() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar 400 quando já existir no banco de dados um cliente com o cpf informado")
    void createCustomerCase1() throws Exception {

        var customer = CustomerBuilder.init().withCpf("98261916049").builder();
        customerRepository.save(customer);

        var dataJson = mapper.writeValueAsString(CreateCustomerBuilder.init().builder());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        var data = mapper.readValue(response.getContentAsString(), ErrorData.class);

        assertThat(data.message()).isEqualTo("Este CPF ja foi cadastrado.");

    }

    @Test
    @DisplayName("Deve retornar 200 quando cadastrar no banco de dados um cliente com as infos válidas")
    void createCustomerCase2() throws Exception {

        var dataJson = mapper.writeValueAsString(CreateCustomerBuilder.init().builder());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var dataResponse = mapper.readValue(response.getContentAsString(), Customer.class);

        assertThat(dataResponse.getCpf()).isEqualTo("98261916049");

    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista vazia")
    void listCustomersCase1() throws Exception {

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");

    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista com 2 clientes")
    void listCustomersCase2() throws Exception {

        var c1 = CustomerBuilder.init().builder();
        var c2 = CustomerBuilder.init()
                .withCpf("00281420041")
                .withName("Any2 Name")
                .withAddress("Any2 Address")
                .withPhone("51999997777")
                .withEmail("any2@email.com")
                .builder();

        customerRepository.save(c1);
        customerRepository.save(c2);

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var contentData = Arrays.asList(
                mapper.readValue(response.getContentAsString(), CustomerList[].class)
        );

        assertThat(contentData.size()).isEqualTo(2);

    }


}