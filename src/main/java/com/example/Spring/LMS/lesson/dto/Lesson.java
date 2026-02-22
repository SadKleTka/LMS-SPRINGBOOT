package com.example.Spring.LMS.lesson.dto;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.tests.test.TestEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Lesson(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String content,
        @NotNull
        String videoUrl,
        @NotNull
        CourseEntity courseEntity,
        TestEntity test,
        List<ProgressEntity> studentsProgressEntities
) {
}
