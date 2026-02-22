package com.example.Spring.LMS.tests.questions.dto;

import jakarta.validation.constraints.NotNull;

public record QuestionToCreate(
        @NotNull
        String name
) {
}
