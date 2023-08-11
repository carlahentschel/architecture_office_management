package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Contract;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePayment(
        @NotNull
        double value,
        @NotBlank
        String quota,
        @NotNull
        LocalDate date,
        @NotNull
        Contract contract
) {
}
