package com.architecture.office.management.architecture_office_management.controllers;

import com.architecture.office.management.architecture_office_management.dtos.CreateUser;
import com.architecture.office.management.architecture_office_management.dtos.ErrorData;
import com.architecture.office.management.architecture_office_management.dtos.UserDetail;
import com.architecture.office.management.architecture_office_management.models.User;
import com.architecture.office.management.architecture_office_management.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid CreateUser data) {
        if(userRepository.existsByUsername(data.username())) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário com este 'username' já existe."));
        }

        var user = new User(
                null,
                data.username(),
                passwordEncoder.encode(data.password())
        );

        userRepository.save(user);

        return ResponseEntity.ok(new UserDetail(user));
    }

}
