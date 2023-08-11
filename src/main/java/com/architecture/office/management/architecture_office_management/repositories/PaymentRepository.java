package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query(value = """
            select
                sum(p.value)
            from
                payments p
            where
                p.date >= :startDate and p.date <= :endDate
            ;
            """,nativeQuery = true)
    double sumPaymentsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
