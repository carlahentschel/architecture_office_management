package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.dtos.ContractsByBudgets;
import com.architecture.office.management.architecture_office_management.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Query(value = """
            select
                count(b.*) as qtBudgets
            from
                budgets b
            left join
                contracts c
            on
                b.id = c.budget_id
            ;
            """,nativeQuery = true)
    int calculateBudgets();
}
