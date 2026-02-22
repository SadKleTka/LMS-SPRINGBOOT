package com.example.Spring.LMS.dto.courseDto;

import com.example.Spring.LMS.entities.CommentEntity;
import com.example.Spring.LMS.entities.EnrollmentEntity;
import com.example.Spring.LMS.entities.LessonEntity;
import com.example.Spring.LMS.entities.UsersEntity;
import com.example.Spring.LMS.enums.CourseLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Course(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String category,
        CourseLevel level,
        @NotNull
        UsersEntity teacher,

        List<LessonEntity> lessonEntities,
        List<EnrollmentEntity> enrollmentEntities,
        List<CommentEntity> commentEntities
) {
}
