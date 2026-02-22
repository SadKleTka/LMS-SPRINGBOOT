package com.example.Spring.LMS.dto;

import jakarta.validation.constraints.NotNull;

public record UserToLogin(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
