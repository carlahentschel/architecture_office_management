package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
    @Query(value = """
            select
                count(c.*) as total_contracts
            from
                contracts c
            where
                signature_date >= :startDate and signature_date <= :endDate
            ;
            """,nativeQuery = true)
    int countContractsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
