package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.Customer;

public record CustomerList(
        String cpf,
        String name,
        String address,
        String phone,
        String email
) {
    public CustomerList(Customer customer) {
        this(
                customer.getCpf(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail()
        );
    }
}
