package com.example.Spring.LMS.users.usersDto;

import com.example.Spring.LMS.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UserToCreate(
        @Null
        Long id,
        @NotNull
        String username,
        @NotNull
        @Email
        String email,
        @NotNull
        String password,
        @NotNull
        UserRole role
) {
}
