package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Boolean existsByCpf(String cpf);

    Customer getReferenceByCpf (String cpf);
}
