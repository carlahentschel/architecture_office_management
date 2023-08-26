package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.models.Customer;

public class CreateCustomerBuilder {
    private String cpf = "98261916049";
    private String name = "Any Name";
    private String address = "Any Address";
    private String phone = "51999998888";
    private String email = "any@email.com";

    public static CreateCustomerBuilder init() {
        return new CreateCustomerBuilder();
    }

    public Customer builder() {
        return new Customer(
                cpf,
                name,
                address,
                phone,
                email
        );
    }
}
