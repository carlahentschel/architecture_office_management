package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.dtos.CreatePayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double value;
    private String quota;
    private LocalDate date;
    @OneToOne
    private Contract contract;

    public Payment(CreatePayment newPayment) {
        value = newPayment.value();
        quota = newPayment.quota();
        date = newPayment.date();
        contract = newPayment.contract();
    }

}
