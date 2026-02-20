package com.example.Spring.LMS.CourseDTO;

import java.time.LocalDateTime;

public record CommentToResponse(
        String name,
        String text,
        LocalDateTime time
) {
}
