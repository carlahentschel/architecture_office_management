package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.dtos.CreateBudget;
import java.time.LocalDate;

public class CreateBudgetBuilder {
    private String customerName = "Any Name" ;
    private String project = "Any Project";
    private String projectStages = "Any Stages";
    private int squareMeters =  111;
    private String workAddress = "Any Address";
    private int estimedHours = 11;
    private LocalDate date = LocalDate.now().plusDays(1);
    private double value = 1111.11;

    public static CreateBudgetBuilder init() {
        return new CreateBudgetBuilder();
    }

    public CreateBudgetBuilder withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public CreateBudgetBuilder withProject(String project) {
        this.project = project;
        return this;
    }

    public CreateBudgetBuilder withProjectStages(String projectStages) {
        this.projectStages = projectStages;
        return this;
    }

    public CreateBudgetBuilder withSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
        return this;
    }

    public CreateBudgetBuilder withWorkAddress(String workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    public CreateBudgetBuilder withEstimedHours(Integer estimedHours) {
        this.estimedHours = estimedHours;
        return this;
    }

    public CreateBudgetBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public CreateBudgetBuilder withValue(Double value) {
        this.value = value;
        return this;
    }

    public CreateBudget builder() {
        return new CreateBudget(
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
