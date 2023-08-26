package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.*;
import com.architecture.office.management.architecture_office_management.models.Customer;
import com.architecture.office.management.architecture_office_management.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody @Valid CreateCustomer data) {

        if(customerRepository.existsByCpf(data.cpf())){
            return ResponseEntity.badRequest().body(new ErrorData("Este CPF ja foi cadastrado."));
        }
        var newCustomer = new Customer(data);
        customerRepository.save(newCustomer);
        return ResponseEntity.ok().body(newCustomer);

    }

    @GetMapping
    public ResponseEntity<List<CustomerList>> listCustomers() {

        var data = customerRepository.findAll().stream().map(CustomerList::new).toList();
        return ResponseEntity.ok().body(data);

    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity updateCustomer(
            @PathVariable String cpf,
            @RequestBody UpdateCustomer customerUpdated
    ) {

        if(!customerRepository.existsByCpf(cpf)) {
            return ResponseEntity.badRequest().body(new ErrorData("Cliente não localizado."));
        }

        var customer = customerRepository.getReferenceByCpf(cpf);
        customer.updateCustomer(customerUpdated);

        return ResponseEntity.noContent().build();
    }

}
