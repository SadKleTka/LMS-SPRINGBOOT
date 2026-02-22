package com.example.Spring.LMS.tests.test.testDto;

import com.example.Spring.LMS.lesson.LessonEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record TestToCreate(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        LessonEntity lesson
) {
}
