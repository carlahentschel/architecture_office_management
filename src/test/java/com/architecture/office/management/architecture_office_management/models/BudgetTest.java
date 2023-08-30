package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.builders.dtos.UpdateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.BudgetBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class BudgetTest {

    @Test
    @DisplayName("Deve atualizar customerName, project, projectStages, squareMeters, workAddress, estimedHours, date e value")
    void updateBudgetCase1() {
        //given
        var budget = BudgetBuilder.init().builder();

        var dataToUpdate = UpdateBudgetBuilder.init().builder();

        //when
        budget.updateBudget(dataToUpdate);

        //then
        assertThat(budget.getCustomerName()).isEqualTo(dataToUpdate.customerName());
        assertThat(budget.getProject()).isEqualTo(dataToUpdate.project());
        assertThat(budget.getProjectStages()).isEqualTo(dataToUpdate.projectStages());
        assertThat(budget.getSquareMeters()).isEqualTo(dataToUpdate.squareMeters().get());
        assertThat(budget.getWorkAddress()).isEqualTo(dataToUpdate.workAddress());
        assertThat(budget.getEstimedHours()).isEqualTo(dataToUpdate.estimedHours().get());
        assertThat(budget.getDate()).isEqualTo(dataToUpdate.date());
        assertThat(budget.getValue()).isEqualTo(dataToUpdate.value().get());
    }

    @Test
    @DisplayName("Deve atualizar squareMeters, estimedHours e value")
    void updateBudgetCase2() {
        //given
        var budget = BudgetBuilder.init().builder();

        var dataToUpdate = UpdateBudgetBuilder.init()
                .withCustomerName(null)
                .withProject(null)
                .withProjectStages(null)
                .withWorkAddress(null)
                .withDate(null)
                .builder();

        //when
        budget.updateBudget(dataToUpdate);

        //then
        assertThat(budget.getCustomerName()).isEqualTo("Any Name");
        assertThat(budget.getProject()).isEqualTo("Any Project");
        assertThat(budget.getProjectStages()).isEqualTo("Any Stages");
        assertThat(budget.getSquareMeters()).isEqualTo(dataToUpdate.squareMeters().get());
        assertThat(budget.getWorkAddress()).isEqualTo("Any Address");
        assertThat(budget.getEstimedHours()).isEqualTo(dataToUpdate.estimedHours().get());
        assertThat(budget.getDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(budget.getValue()).isEqualTo(dataToUpdate.value().get());
    }


}