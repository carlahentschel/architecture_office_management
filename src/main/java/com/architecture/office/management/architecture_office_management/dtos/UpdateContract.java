package com.architecture.office.management.architecture_office_management.dtos;

import java.time.LocalDate;

public record UpdateContract(
        String formOfPayment,
        LocalDate initialDate,
        LocalDate finalDate,
        LocalDate signatureDate
) {
}
