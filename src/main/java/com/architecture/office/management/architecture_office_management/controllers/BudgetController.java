package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.*;
import com.architecture.office.management.architecture_office_management.repositories.BudgetRepository;
import com.architecture.office.management.architecture_office_management.models.Budget;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createBudget(@RequestBody @Valid CreateBudget data) {

        var newBudget = new Budget(data);
        budgetRepository.save(newBudget);

        return ResponseEntity.ok().body(newBudget);
    }

    @GetMapping
    public ResponseEntity<List<BudgetList>> listBudgets() {

        var data = budgetRepository.findAll().stream().map(BudgetList::new).toList();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/count_budgets")
    public ResponseEntity<Integer> countBudgets(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {

        var data = budgetRepository.countBudgetsInDateRange(startDate, endDate);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateBudget(@PathVariable UUID id, @RequestBody UpdateBudget budgetUpdated) {

        if(!budgetRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ErrorData("Orçamento não localizado."));
        }

        var budget = budgetRepository.getReferenceById(id);
        budget.updateBudget(budgetUpdated);

        return ResponseEntity.noContent().build();
    }

}
