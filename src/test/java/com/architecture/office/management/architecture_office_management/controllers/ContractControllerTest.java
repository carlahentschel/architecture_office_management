package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateContractBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.BudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.ContractBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.CustomerBuilder;
import com.architecture.office.management.architecture_office_management.dtos.ContractList;
import com.architecture.office.management.architecture_office_management.models.Budget;
import com.architecture.office.management.architecture_office_management.repositories.BudgetRepository;
import com.architecture.office.management.architecture_office_management.repositories.ContractRepository;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void afterEach() {
        contractRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar status 200 contendo um contrato criado quando as infos são válidas")
    void createContract() throws Exception {
        //given
        var budget = BudgetBuilder.init().builder();
        var budgetSaved = budgetRepository.save(budget);

        var customer = CustomerBuilder.init().builder();
        customerRepository.save(customer);

        var dataJson = mapper.writeValueAsString(CreateContractBuilder.init().withBudget(budgetSaved).builder());

        //when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.post("/contracts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var dataResponse = mapper.readValue(response.getContentAsString(), Budget.class);

        assertThat(dataResponse.getId()).isNotNull();
        assertThat(dataResponse.getId().getClass()).isEqualTo(UUID.class);
    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista vazia")
    void listContractsCase1() throws Exception {
        // given

        // when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/contracts")
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista com 1 contrato")
    void listContractsCase2() throws Exception {
        // given
        var budget = BudgetBuilder.init().builder();
        var budgetSaved = budgetRepository.save(budget);

        var c1 = ContractBuilder.init().withBudget(budgetSaved).builder();
        contractRepository.save(c1);

        // when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/contracts")
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var contentData = Arrays.asList(
                mapper.readValue(response.getContentAsString(), ContractList[].class)
        );

        assertThat(contentData.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Deve retornar status 200 com 1 orçamento no intervalo de datas passado")
    void countContractsCase1() throws Exception {
        //given
        var b1 = BudgetBuilder.init().builder();
        budgetRepository.save(b1);
        var c1 = ContractBuilder.init().withBudget(b1).builder();
        contractRepository.save(c1);
        var today = LocalDate.now();

        //then
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/contracts/count_contracts")
                        .param("startDate", today.toString() )
                        .param("endDate", today.plusDays(2).toString())
        ).andReturn().getResponse();

        //when
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1");
    }
}