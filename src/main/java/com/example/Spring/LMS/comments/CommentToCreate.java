package com.example.Spring.LMS.comments;

import jakarta.validation.constraints.NotNull;

public record CommentToCreate(
        @NotNull
        String text
) {
}
