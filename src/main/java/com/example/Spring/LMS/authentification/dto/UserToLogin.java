package com.example.Spring.LMS.authentification.dto;

import jakarta.validation.constraints.NotNull;

public record UserToLogin(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
