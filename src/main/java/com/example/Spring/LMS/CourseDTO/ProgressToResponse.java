package com.example.Spring.LMS.CourseDTO;

import com.example.Spring.LMS.enums.StatusOfProgress;

import java.time.LocalDateTime;

public record ProgressToResponse(
        String lessonName,
        String name,
        StatusOfProgress status,
        LocalDateTime date
) {
}
