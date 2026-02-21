package com.example.Spring.LMS.TestDTO;

import jakarta.validation.constraints.NotNull;

public record QuestionToCreate(
        @NotNull
        String name
) {
}
