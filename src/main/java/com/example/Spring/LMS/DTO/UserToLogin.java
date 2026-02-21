package com.example.Spring.LMS.DTO;

import jakarta.validation.constraints.NotNull;

public record UserToLogin(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
