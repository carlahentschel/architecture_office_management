package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.dtos.UpdateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.BudgetBuilder;
import com.architecture.office.management.architecture_office_management.dtos.BudgetList;
import com.architecture.office.management.architecture_office_management.models.Budget;
import com.architecture.office.management.architecture_office_management.repositories.BudgetRepository;
import com.architecture.office.management.architecture_office_management.repositories.ContractRepository;
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
class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ContractRepository contractRepository;

    @AfterEach
    public void afterEach() {
        contractRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar status 200 contendo um orçamento criado quando as infos são válidas")
    public void createBudget() throws Exception {
        //given
        var dataJson = mapper.writeValueAsString(CreateBudgetBuilder.init().builder());

        //when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.post("/budgets")
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
    public void listBudgetsCase1() throws Exception {
        // given

        // when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/budgets")
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    @DisplayName("Deve retornar status 200 com uma lista com 2 orçamentos")
    public void listBudgetsCase2() throws Exception {
        // given
        var b1 = BudgetBuilder.init().builder();
        var b2 = BudgetBuilder.init()
                .withCustomerName("Any2 Name")
                .withProject("Any2 Project")
                .withProjectStages("Any2 Stages")
                .withSquareMeters(222)
                .withWorkAddress("Any2 Address")
                .withEstimedHours(22)
                .withDate(LocalDate.of(2023,8,23))
                .withValue(2222.22)
                .builder();

        budgetRepository.save(b1);
        budgetRepository.save(b2);

        // when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/budgets")
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var contentData = Arrays.asList(
                mapper.readValue(response.getContentAsString(), BudgetList[].class)
        );

        assertThat(contentData.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Deve retornar status 200 com 1 orçamento no intervalo de datas passado")
    public void countBudgetsCase1() throws Exception {
        //given
        var b1 = BudgetBuilder.init().builder();
        budgetRepository.save(b1);
        var today = LocalDate.now();

        //then
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/budgets/count_budgets")
                        .param("startDate", today.toString() )
                        .param("endDate", today.plusDays(2).toString())
        ).andReturn().getResponse();

        //when
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1");
    }

    @Test
    @DisplayName("Deve retornar status 200 com 'zero' orçamento no intervalo de datas passado")
    public void countBudgetsCase2() throws Exception {
        //given
        var b1 = BudgetBuilder.init().builder();
        budgetRepository.save(b1);

        //then
        var response = mockMvc.perform(
                MockMvcRequestBuilders.get("/budgets/count_budgets")
                        .param("startDate", "2023-07-01")
                        .param("endDate", "2023-07-31")
        ).andReturn().getResponse();

        //when
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }

    @Test
    @DisplayName("Deve retornar 404 quando acessar um id de orçamento inexistente a ser atualizado")
    public void updateBudget() throws Exception {
        //given
        var id = UUID.randomUUID();
        var dataJson = mapper.writeValueAsString(UpdateBudgetBuilder.init().builder());

        //when
        var response = mockMvc.perform(
                MockMvcRequestBuilders.put("/budgets/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

}