package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Budget;
import java.time.LocalDate;
import java.util.UUID;

public record BudgetList(
        UUID id,
        String customerName,
        String project,
        String projectStages,
        int squareMeters,
        String workAddress,
        int estimedHours,
        LocalDate date,
        double value
) {

    public BudgetList(Budget budget) {
        this(
                budget.getId(),
                budget.getCustomerName(),
                budget.getProject(),
                budget.getProjectStages(),
                budget.getSquareMeters(),
                budget.getWorkAddress(),
                budget.getEstimedHours(),
                budget.getDate(),
                budget.getValue()
        );
    }

}
