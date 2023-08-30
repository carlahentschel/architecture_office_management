package com.architecture.office.management.architecture_office_management.builders.dtos;

import com.architecture.office.management.architecture_office_management.dtos.UpdateBudget;
import java.time.LocalDate;
import java.util.Optional;

public class UpdateBudgetBuilder {

    private String customerName = "Any Name Updated" ;
    private String project = "Any Project Updated";
    private String projectStages = "Any Stages Updated";
    private int squareMeters =  222;
    private String workAddress = "Any Address Updated";
    private int estimedHours = 22;
    private LocalDate date =  LocalDate.now().plusDays(2);
    private double value = 2222.22;

    public static UpdateBudgetBuilder init() {
        return new UpdateBudgetBuilder();
    }

    public UpdateBudget builder() {
        return new UpdateBudget(
                customerName,
                project,
                projectStages,
                Optional.of(squareMeters),
                workAddress,
                Optional.of(estimedHours),
                date,
                Optional.of(value)
        );
    }

    public UpdateBudgetBuilder withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public UpdateBudgetBuilder withProject(String project) {
        this.project = project;
        return this;
    }

    public UpdateBudgetBuilder withProjectStages(String projectStages) {
        this.projectStages = projectStages;
        return this;
    }

    public UpdateBudgetBuilder withWorkAddress(String workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    public UpdateBudgetBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

}
