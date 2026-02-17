package com.example.Spring.LMS.LessonDTO;

public record LessonResponse(
        Long id,
        String name,
        String content,
        String videoUrl
) {
}
