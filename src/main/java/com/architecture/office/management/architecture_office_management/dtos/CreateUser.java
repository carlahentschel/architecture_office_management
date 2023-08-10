package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUser(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
