package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.dtos.CreateCustomer;
import com.architecture.office.management.architecture_office_management.dtos.UpdateCustomer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String cpf;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Customer(CreateCustomer newCustomer) {
        cpf = newCustomer.cpf();
        name = newCustomer.name();
        address = newCustomer.address();
        phone = newCustomer.phone();
        email = newCustomer.email();
    }

    public void updateCustomer(UpdateCustomer data) {
        if(data.name() != null) {
            name = data.name();
        }
        if(data.address() != null) {
            address = data.address();
        }
        if(data.phone() != null) {
            phone = data.phone();
        }
        if(data.email() != null) {
            email = data.email();
        }
    }
}
