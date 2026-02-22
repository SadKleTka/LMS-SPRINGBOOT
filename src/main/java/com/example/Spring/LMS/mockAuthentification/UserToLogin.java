package com.example.Spring.LMS.mockAuthentification;

import jakarta.validation.constraints.NotNull;

public record UserToLogin(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
