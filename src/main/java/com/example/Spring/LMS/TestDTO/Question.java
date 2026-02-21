package com.example.Spring.LMS.TestDTO;

import com.example.Spring.LMS.entities.AnswerOptionEntity;
import com.example.Spring.LMS.entities.TestEntity;
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
