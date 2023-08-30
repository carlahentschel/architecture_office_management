package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.dtos.UpdateContract;
import java.time.LocalDate;


public class UpdateContractBuilder {
    private String formOfPayment = "Any_form_updated";
    private LocalDate initialDate = LocalDate.now().plusDays(2);
    private LocalDate finalDate =  LocalDate.now().plusMonths(2);
    private LocalDate signatureDate =  LocalDate.now().plusDays(2);

    public static UpdateContractBuilder init() {
        return new UpdateContractBuilder();
    }

    public UpdateContract builder() {
        return new UpdateContract(
                formOfPayment,
                initialDate,
                finalDate,
                signatureDate
        );
    }
}
