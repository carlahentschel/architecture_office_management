package com.architecture.office.management.architecture_office_management.builders.models;

import com.architecture.office.management.architecture_office_management.models.Contract;
import com.architecture.office.management.architecture_office_management.models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerBuilder {
    private String cpf = "98261916049";
    private String name = "Any Name";
    private String address = "Any Address";
    private String phone = "51999998888";
    private String email = "any@email.com";
    private List<Contract> contracts = new ArrayList<>();

    public static CustomerBuilder init() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer builder() {
        return new Customer(
                cpf,
                name,
                address,
                phone,
                email,
                contracts
        );
    }
}
