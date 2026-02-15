package com.example.Spring.LMS.records;

import com.example.Spring.LMS.entitys.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record Answers(
        @Null
        Long id,
        @NotNull
        String text,
        Boolean isCorrect,
        @Null
        QuestionEntity questionEntity
) {
}
