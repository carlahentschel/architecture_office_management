package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.CreateBudget;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @PostMapping
    @Transactional
    public ResponseEntity createBudget(@RequestBody @Valid CreateBudget budgetDto) {

        return ResponseEntity.noContent().build();

    }
}
