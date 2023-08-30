package com.architecture.office.management.architecture_office_management.builders.models;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateContractBuilder;
import com.architecture.office.management.architecture_office_management.builders.dtos.CreatePaymentBuilder;
import com.architecture.office.management.architecture_office_management.dtos.CreatePayment;
import com.architecture.office.management.architecture_office_management.models.Contract;
import com.architecture.office.management.architecture_office_management.models.Payment;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentBuilder {

    private UUID id;
    private double value = 1000;
    private String quota = "Any quota";
    private LocalDate date = LocalDate.now();
    private Contract contract = new Contract(CreateContractBuilder.init().builder());

    public static PaymentBuilder init() {
        return new PaymentBuilder();
    }

    public Payment builder() {
        return new Payment(
                null,
                value,
                quota,
                date,
                contract
        );
    }

    public PaymentBuilder withContract(Contract contract) {
        this.contract = contract;
        return this;
    }
}
