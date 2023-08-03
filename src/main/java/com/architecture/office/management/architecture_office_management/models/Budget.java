package com.architecture.office.management.architecture_office_management.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
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
    @Column(name = "work_address")
    private String workAddress;
    @Column(name = "estimated_hours")
    private int estimedHours;
    private LocalDate date;
    private double value;
}
