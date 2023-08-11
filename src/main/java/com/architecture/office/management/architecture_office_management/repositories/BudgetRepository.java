package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
    @Query(value = """
            select
                count(b.*) as total_budgets
            from
                budgets b
            where
                date >= :startDate and date <= :endDate
            ;
            """,nativeQuery = true)
    int countBudgetsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
