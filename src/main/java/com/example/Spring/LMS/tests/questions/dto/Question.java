package com.example.Spring.LMS.tests.questions.dto;

import com.example.Spring.LMS.tests.answers.AnswerOptionEntity;
import com.example.Spring.LMS.tests.test.TestEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Question(
        @Null
        Long id,
        @NotNull
        String text,
        @NotNull
        TestEntity test,
        @NotNull
        List<AnswerOptionEntity> answers
) {
}
