package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Budget;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateContract(
        @NotBlank
        String formOfPayment,
        @NotNull
        LocalDate initialDate,
        @NotNull
        LocalDate finalDate,
        @NotNull
        Budget budget,
        @NotBlank
        String customerCpf,
        @NotNull
        LocalDate signatureDate
) {
}
