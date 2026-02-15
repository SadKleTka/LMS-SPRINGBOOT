package com.example.Spring.LMS.records;

import com.example.Spring.LMS.entitys.CommentEntity;
import com.example.Spring.LMS.entitys.EnrollmentEntity;
import com.example.Spring.LMS.entitys.LessonEntity;
import com.example.Spring.LMS.entitys.UsersEntity;
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
