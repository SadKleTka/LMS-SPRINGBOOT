package com.example.Spring.LMS.progresses;

import com.example.Spring.LMS.enums.StatusOfProgress;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProgressToResponse(
        @NotNull
        String lessonName,
        @NotNull
        String name,
        @NotNull
        StatusOfProgress status,
        @NotNull
        @FutureOrPresent
        LocalDateTime date
) {
}
