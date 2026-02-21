package com.example.Spring.LMS.TestDTO;

import jakarta.validation.constraints.NotNull;

public record AnswersToAnswer(
        @NotNull
        String text,
        @NotNull
        Boolean isCorrect
) {
}
