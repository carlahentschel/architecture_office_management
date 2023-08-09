package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.dtos.CreateContract;
import com.architecture.office.management.architecture_office_management.dtos.UpdateContract;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String formOfPayment;
    private LocalDate initialDate;
    private LocalDate finalDate;
    @OneToOne
    private Budget budget;
    @Column(name = "customer_cpf")
    private String customerCpf;

    public Contract(CreateContract newContract) {
        formOfPayment = newContract.formOfPayment();
        initialDate = newContract.initialDate();
        finalDate = newContract.finalDate();
        budget = newContract.budget();
        customerCpf = newContract.customerCpf();
    }

    public void updateContract(UpdateContract data) {

        if(data.formOfPayment() != null) {
            formOfPayment = data.formOfPayment();
        }

        if(data.initialDate() != null) {
            initialDate = data.initialDate();
        }

        if(data.finalDate() != null) {
            finalDate = data.finalDate();
        }

    }



}
