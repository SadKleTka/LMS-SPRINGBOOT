package com.example.Spring.LMS.LessonDTO;

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
