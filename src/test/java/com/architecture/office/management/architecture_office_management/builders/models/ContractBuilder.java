package com.architecture.office.management.architecture_office_management.builders.models;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.models.Budget;
import com.architecture.office.management.architecture_office_management.models.Contract;
import java.time.LocalDate;
import java.util.UUID;

public class ContractBuilder {

    private UUID id;
    private String formOfPayment = "Any_form";
    private LocalDate initialDate = LocalDate.now().plusDays(1);
    private LocalDate finalDate =  LocalDate.now().plusMonths(1);
    private Budget budget = new Budget(CreateBudgetBuilder.init().builder());
    private String customerCpf = "98261916049";
    private LocalDate signatureDate =  LocalDate.now().plusDays(1);

    public static ContractBuilder init() {
        return new ContractBuilder();
    }

    public Contract builder() {
        return new Contract(
                null,
                formOfPayment,
                initialDate,
                finalDate,
                budget,
                customerCpf,
                signatureDate
        );
    }

    public ContractBuilder withBudget(Budget budget) {
        this.budget = budget;
        return this;
    }

    public ContractBuilder withCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
        return this;
    }
}
