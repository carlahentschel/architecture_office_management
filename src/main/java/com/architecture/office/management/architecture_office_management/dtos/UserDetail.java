package com.architecture.office.management.architecture_office_management.dtos;

import com.architecture.office.management.architecture_office_management.models.User;
import java.util.UUID;

public record UserDetail(
        UUID id,
        String username,
        String password
) {
    public UserDetail(User user) {
        this(user.getId(), user.getUsername(), user.getPassword());
    }
}
