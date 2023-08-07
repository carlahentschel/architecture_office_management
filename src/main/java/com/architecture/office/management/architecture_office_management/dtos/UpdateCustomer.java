package com.architecture.office.management.architecture_office_management.dtos;

public record UpdateCustomer(
        String name,
        String address,
        String phone,
        String email
) {
}
