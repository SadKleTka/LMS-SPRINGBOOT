package com.example.Spring.LMS.DTO;

import com.example.Spring.LMS.enums.UserRole;

public record UserResponse(
        Long id,
        String name,
        UserRole role
) {
}
