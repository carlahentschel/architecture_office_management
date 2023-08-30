package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.dtos.CreateContract;
import com.architecture.office.management.architecture_office_management.dtos.CreatePayment;
import com.architecture.office.management.architecture_office_management.models.Budget;
import com.architecture.office.management.architecture_office_management.models.Contract;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.UUID;

public class CreatePaymentBuilder {
    private double value = 1000;
    private String quota = "Any quota";
    private LocalDate date = LocalDate.now();
    private Contract contract = new Contract(CreateContractBuilder.init().builder());

    public static CreatePaymentBuilder init() {
        return new CreatePaymentBuilder();
    }

    public CreatePayment builder() {
        return new CreatePayment(
                value,
                quota,
                date,
                contract
        );
    }

    public CreatePaymentBuilder withContract(Contract contract) {
        this.contract = contract;
        return this;
    }
}
