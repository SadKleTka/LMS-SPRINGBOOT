package com.example.Spring.LMS.tests.answers.answersDto;

import jakarta.validation.constraints.NotNull;

public record AnswersToAnswer(
        @NotNull
        String text,
        @NotNull
        Boolean isCorrect
) {
}
