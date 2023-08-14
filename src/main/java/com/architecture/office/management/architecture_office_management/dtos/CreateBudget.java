package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.validation.constraints.*;
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
        @Min(value = 1)
        double value
) {
}
