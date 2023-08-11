package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Contract;
import com.architecture.office.management.architecture_office_management.models.Payment;
import java.time.LocalDate;
import java.util.UUID;

public record PaymentList(
        UUID id,
        double value,
        String quota,
        LocalDate date,
        Contract contract
) {

    public PaymentList(Payment payment) {
        this(
                payment.getId(),
                payment.getValue(),
                payment.getQuota(),
                payment.getDate(),
                payment.getContract()
        );
    }
}
