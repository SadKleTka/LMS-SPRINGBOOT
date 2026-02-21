package com.example.Spring.LMS.CourseDTO;

import jakarta.validation.constraints.NotNull;

public record CommentToCreate(
        @NotNull
        String text
) {
}
