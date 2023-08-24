package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.builders.dtos.UpdateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.builders.models.BudgetBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BudgetTest {

    // não está passando
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
        assertThat(budget.getSquareMeters()).isEqualTo(dataToUpdate.squareMeters());
        assertThat(budget.getWorkAddress()).isEqualTo(dataToUpdate.workAddress());
        assertThat(budget.getEstimedHours()).isEqualTo(dataToUpdate.estimedHours());
        assertThat(budget.getDate()).isEqualTo(dataToUpdate.date());
        assertThat(budget.getValue()).isEqualTo(dataToUpdate.value());
    }
}