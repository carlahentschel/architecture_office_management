package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.CreatePayment;
import com.architecture.office.management.architecture_office_management.dtos.PaymentList;
import com.architecture.office.management.architecture_office_management.models.Payment;
import com.architecture.office.management.architecture_office_management.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createPayment(@RequestBody @Valid CreatePayment data) {

        var newPayment = new Payment(data);
        paymentRepository.save(newPayment);

        return ResponseEntity.ok().body(newPayment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentList>> listPayments() {

        var data = paymentRepository.findAll().stream().map(PaymentList::new).toList();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/sum_payments")
    public ResponseEntity<Double> sumPayments(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {

        var data = paymentRepository.sumPaymentsInDateRange(startDate, endDate);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/payments_per_period")
    public ResponseEntity<List<Payment>> findPaymentsInDateRange(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {

        List<Payment> data = paymentRepository.paymentsInDateRange(startDate, endDate);
        return ResponseEntity.ok().body(data);
    }

}
