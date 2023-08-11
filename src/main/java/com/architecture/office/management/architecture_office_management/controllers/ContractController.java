package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.ContractList;
import com.architecture.office.management.architecture_office_management.dtos.CreateContract;
import com.architecture.office.management.architecture_office_management.dtos.ErrorData;
import com.architecture.office.management.architecture_office_management.dtos.UpdateContract;
import com.architecture.office.management.architecture_office_management.models.Contract;
import com.architecture.office.management.architecture_office_management.repositories.ContractRepository;
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
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createContract(@RequestBody @Valid CreateContract data) {

        var newContract = new Contract(data);
        contractRepository.save(newContract);

        return ResponseEntity.ok().body(newContract);
    }

    @GetMapping
    public ResponseEntity<List<ContractList>> listContracts() {

        var data = contractRepository.findAll().stream().map(ContractList::new).toList();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/count_contracts")
    public ResponseEntity<Integer> countContracts(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {

        var data = contractRepository.countContractsInDateRange(startDate, endDate);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateContract(@PathVariable UUID id, @RequestBody UpdateContract contractUpdated) {

        if(!contractRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ErrorData("Contrato não localizado."));
        }

        var contract = contractRepository.getReferenceById(id);
        contract.updateContract(contractUpdated);

        return ResponseEntity.noContent().build();

    }

}
