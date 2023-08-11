package com.architecture.office.management.architecture_office_management.repositories;

import com.architecture.office.management.architecture_office_management.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
