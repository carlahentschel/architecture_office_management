package com.architecture.office.management.architecture_office_management.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateContract(
        String formOfPayment,
        LocalDate initialDate,
        LocalDate finalDate
) {
}
