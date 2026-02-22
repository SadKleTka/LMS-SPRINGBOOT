package com.example.Spring.LMS.tests.questions.questionDto;

import jakarta.validation.constraints.NotNull;

public record QuestionToCreate(
        @NotNull
        String name
) {
}
