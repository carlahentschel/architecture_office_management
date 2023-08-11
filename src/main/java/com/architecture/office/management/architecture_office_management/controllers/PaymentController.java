package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.CreatePayment;
import com.architecture.office.management.architecture_office_management.models.Payment;
import com.architecture.office.management.architecture_office_management.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
