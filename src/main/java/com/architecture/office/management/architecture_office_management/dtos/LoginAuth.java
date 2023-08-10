package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginAuth(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
