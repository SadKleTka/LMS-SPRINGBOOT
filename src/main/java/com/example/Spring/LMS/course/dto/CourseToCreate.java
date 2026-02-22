package com.example.Spring.LMS.course.dto;

import com.example.Spring.LMS.enums.CourseLevel;
import jakarta.validation.constraints.NotNull;

public record CourseToCreate(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String category,
        @NotNull
        CourseLevel level
) {
}
