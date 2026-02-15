package com.example.Spring.LMS.records;

import com.example.Spring.LMS.entitys.LessonEntity;
import com.example.Spring.LMS.entitys.UsersEntity;
import com.example.Spring.LMS.enums.StatusOfProgress;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record Progress(
        @Null
        Long id,
        @NotNull
        @FutureOrPresent
        LocalDateTime completedAt,
        StatusOfProgress status,
        @NotNull
        UsersEntity student,
        @NotNull
        LessonEntity lessonEntity
) {
}
