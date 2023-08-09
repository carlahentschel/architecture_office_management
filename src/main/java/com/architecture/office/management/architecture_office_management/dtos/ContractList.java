package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Budget;
import com.architecture.office.management.architecture_office_management.models.Contract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ContractList(
        UUID id,
        String formOfPayment,
        LocalDate initialDate,
        LocalDate finalDate,
        Budget budget,
        String customerCpf
) {
    public ContractList(Contract contract) {
        this(
                contract.getId(),
                contract.getFormOfPayment(),
                contract.getInitialDate(),
                contract.getFinalDate(),
                contract.getBudget(),
                contract.getCustomerCpf()
        );

    }

}
