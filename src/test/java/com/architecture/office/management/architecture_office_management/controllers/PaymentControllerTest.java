package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreatePaymentBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.BudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.ContractBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.CustomerBuilder;
import com.architecture.office.management.architecture_office_management.repositories.BudgetRepository;
import com.architecture.office.management.architecture_office_management.repositories.ContractRepository;
import com.architecture.office.management.architecture_office_management.repositories.CustomerRepository;
import com.architecture.office.management.architecture_office_management.repositories.PaymentRepository;
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
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void afterEach() {
        paymentRepository.deleteAll();
        customerRepository.deleteAll();
        contractRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar status 200 contendo um pagamento cadastrado quando as infos são válidas")
    void createPayment() throws Exception {

        var budget = BudgetBuilder.init().builder();
        var budgetSaved = budgetRepository.save(budget);

        var customer = CustomerBuilder.init().builder();
        customerRepository.save(customer);

        var contract = ContractBuilder.init()
                .withBudget(budgetSaved)
                .withCustomerCpf(customer.getCpf())
                .builder();
        var contractSaved = contractRepository.save(contract);

        var dataJson = mapper.writeValueAsString(CreatePaymentBuilder.init()
                .withContract(contractSaved).builder());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista vazia")
    void listPaymentsCase1() throws Exception {

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/payments")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");

    }

}