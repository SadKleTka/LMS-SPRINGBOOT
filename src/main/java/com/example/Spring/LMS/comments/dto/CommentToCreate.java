package com.example.Spring.LMS.comments.dto;

import jakarta.validation.constraints.NotNull;

public record CommentToCreate(
        @NotNull
        String text
) {
}
