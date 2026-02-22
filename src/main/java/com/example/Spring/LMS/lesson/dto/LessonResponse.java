package com.example.Spring.LMS.lesson.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record LessonResponse(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String content,
        @NotNull
        String videoUrl
) {
}
