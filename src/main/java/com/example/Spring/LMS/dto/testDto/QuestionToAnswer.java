package com.example.Spring.LMS.dto.testDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record QuestionToAnswer(
        @Null
        Long id,
        @NotNull
        String text,
        @NotNull
        List<AnswersToAnswer> answers
) {
}
