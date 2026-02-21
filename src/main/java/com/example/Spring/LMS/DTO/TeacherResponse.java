package com.example.Spring.LMS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record TeacherResponse(
        @NotNull
        String name,
        @NotNull
        @Email
        String email
) {
}
