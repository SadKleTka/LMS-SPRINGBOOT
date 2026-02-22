package com.example.Spring.LMS.tests.questions.dto;

import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
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
