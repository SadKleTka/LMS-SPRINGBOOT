package com.example.Spring.LMS.DTO;

import com.example.Spring.LMS.enums.UserRole;
import jakarta.validation.constraints.Email;

public record UserToCreate(
        Long id,
        String username,
        @Email
        String email,
        String password,
        UserRole role
) {
}
