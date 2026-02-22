package com.example.Spring.LMS.dto.courseDto;

import jakarta.validation.constraints.NotNull;

public record CommentToCreate(
        @NotNull
        String text
) {
}
