package com.architecture.office.management.architecture_office_management.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CreateCustomer(
        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @Email
        @NotBlank
        String email
) {
}
