package com.example.Spring.LMS.LessonDTO;

import com.example.Spring.LMS.entities.CourseEntity;
import com.example.Spring.LMS.entities.ProgressEntity;
import com.example.Spring.LMS.entities.TestEntity;
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
