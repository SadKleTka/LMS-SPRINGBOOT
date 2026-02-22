package com.example.Spring.LMS.dto;

import com.example.Spring.LMS.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UserResponse(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        UserRole role
) {
}
