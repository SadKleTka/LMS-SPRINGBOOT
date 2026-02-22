package com.example.Spring.LMS.lesson.dto;

import jakarta.validation.constraints.NotNull;

public record LessonToCreate(
        @NotNull
        String name,
        @NotNull
        String content,
        @NotNull
        String videoUrl
) {
}
