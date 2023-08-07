package com.architecture.office.management.architecture_office_management.models;

import com.architecture.office.management.architecture_office_management.dtos.CreateBudget;
import com.architecture.office.management.architecture_office_management.dtos.UpdateBudget;
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
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "customer_name")
    private String customerName;
    private String project;
    @Column(name = "project_stages")
    private String projectStages;
    @Column(name = "square_meters")
    private int squareMeters;
    @Column(name = "work_address")
    private String workAddress;
    @Column(name = "estimated_hours")
    private int estimedHours;
    private LocalDate date;
    private double value;
    //private int version;

    public Budget(CreateBudget newBudget) {
        customerName = newBudget.customerName();
        project = newBudget.project();
        projectStages = newBudget.projectStages();
        squareMeters = newBudget.squareMeters();
        workAddress = newBudget.workAddress();
        estimedHours = newBudget.estimedHours();
        date = newBudget.date();
        value = newBudget.value();
        //version = 1;
    }

    public void updateBudget(UpdateBudget data) {

        if(data.customerName() != null) {
            customerName = data.customerName();
        }

        if(data.project() != null) {
            project = data.project();
        }

        if(data.projectStages() != null) {
            projectStages = data.projectStages();
        }

        if(data.squareMeters().isPresent()) {
            squareMeters = data.squareMeters().get();
        }

        if(data.workAddress() != null) {
            workAddress = data.workAddress();
        }

        if(data.estimedHours().isPresent()) {
            estimedHours = data.estimedHours().get();
        }

        if(data.date() != null) {
            date = data.date();
        }

        if(data.value().isPresent()) {
            value = data.value().get();
        }

        //version += 1;

    }


}
