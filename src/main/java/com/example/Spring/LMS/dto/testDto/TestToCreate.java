package com.example.Spring.LMS.dto.testDto;

import com.example.Spring.LMS.entities.LessonEntity;
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
