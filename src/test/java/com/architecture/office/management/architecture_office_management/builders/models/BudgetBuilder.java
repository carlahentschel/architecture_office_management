package com.architecture.office.management.architecture_office_management.builders.models;

import com.architecture.office.management.architecture_office_management.builders.dtos.CreateBudgetBuilder;
import com.architecture.office.management.architecture_office_management.models.Budget;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

public class BudgetBuilder {
    private UUID id;
    private String customerName = "Any Name";
    private String project = "Any Project";
    private String projectStages = "Any Stages";
    private int squareMeters =  111;
    private String workAddress = "Any Address";
    private int estimedHours = 11;
    private LocalDate date = LocalDate.of(2023,8,23);
    private double value = 1111.11;

    public static BudgetBuilder init() {
        return new BudgetBuilder();
    }

    public BudgetBuilder withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public BudgetBuilder withProject(String project) {
        this.project = project;
        return this;
    }

    public BudgetBuilder withProjectStages(String projectStages) {
        this.projectStages = projectStages;
        return this;
    }

    public BudgetBuilder withSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
        return this;
    }

    public BudgetBuilder withWorkAddress(String workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    public BudgetBuilder withEstimedHours(Integer estimedHours) {
        this.estimedHours = estimedHours;
        return this;
    }

    public BudgetBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BudgetBuilder withValue(Double value) {
        this.value = value;
        return this;
    }

    public Budget builder() {
        return new Budget(
                null,
                customerName,
                project,
                projectStages,
                squareMeters,
                workAddress,
                estimedHours,
                date,
                value
        );
    }
}
