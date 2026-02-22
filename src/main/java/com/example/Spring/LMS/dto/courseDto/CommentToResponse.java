package com.example.Spring.LMS.dto.courseDto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommentToResponse(
        @NotNull
        String name,
        @NotNull
        String text,
        @NotNull
        @FutureOrPresent
        LocalDateTime time
) {
}
