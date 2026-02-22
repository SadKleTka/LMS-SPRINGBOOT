package com.example.Spring.LMS.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TeacherResponse(
        @NotNull
        String username,
        @NotNull
        @Email
        String email
) {
}
