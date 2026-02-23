package com.example.Spring.LMS.tests.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record TestToCreate(
        @Null
        Long id,
        @NotNull
        String name
) {
}
