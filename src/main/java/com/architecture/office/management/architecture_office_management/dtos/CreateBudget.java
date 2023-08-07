package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

public record CreateBudget(
        @NotBlank
        @Length(min = 3, max = 100)
        String customerName,
        @NotBlank
        @Length(min = 3, max = 100)
        String project,
        @NotBlank
        @Length(min = 3)
        String projectStages,
        @NotNull
        int squareMeters,
        @NotBlank
        @Length(min = 3, max = 100)
        String workAddress,
        @NotNull
        int estimedHours,
        @NotNull
        @FutureOrPresent
        LocalDate date,
        @NotNull
        double value
) {
}
