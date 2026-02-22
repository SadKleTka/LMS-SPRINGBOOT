package com.example.Spring.LMS.dto.testDto;

import jakarta.validation.constraints.NotNull;

public record AnswersToAnswer(
        @NotNull
        String text,
        @NotNull
        Boolean isCorrect
) {
}
