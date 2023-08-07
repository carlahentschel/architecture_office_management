package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateBudget(
        String customerName,
        String project,
        String projectStages,
        Optional<Integer> squareMeters,
        String workAddress,
        Optional<Integer> estimedHours,
        LocalDate date,
        Optional<Double> value
) {
}
