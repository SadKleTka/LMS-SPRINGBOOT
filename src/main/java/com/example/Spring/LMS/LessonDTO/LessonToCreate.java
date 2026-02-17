package com.example.Spring.LMS.LessonDTO;

public record LessonToCreate(
        String name,
        String content,
        String videoUrl
) {
}
