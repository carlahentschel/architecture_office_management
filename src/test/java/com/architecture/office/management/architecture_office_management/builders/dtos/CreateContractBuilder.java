package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.dtos.CreateContract;
import com.architecture.office.management.architecture_office_management.models.Budget;
import java.time.LocalDate;

public class CreateContractBuilder {
    private String formOfPayment = "Any_form";
    private LocalDate initialDate = LocalDate.now().plusDays(1);
    private LocalDate finalDate =  LocalDate.now().plusMonths(1);
    private Budget budget = new Budget(CreateBudgetBuilder.init().builder());
    private String customerCpf = "98261916049";
    private LocalDate signatureDate =  LocalDate.now().plusDays(1);

    public static CreateContractBuilder init() {
        return new CreateContractBuilder();
    }

    public CreateContract builder() {
        return new CreateContract(
                formOfPayment,
                initialDate,
                finalDate,
                budget,
                customerCpf,
                signatureDate
        );
    }

    public CreateContractBuilder withBudget(Budget budget) {
        this.budget = budget;
        return this;
    }
}
