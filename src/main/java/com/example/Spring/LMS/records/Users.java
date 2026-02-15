package com.example.Spring.LMS.records;

import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.entitys.CourseEntity;
import com.example.Spring.LMS.entitys.EnrollmentEntity;
import com.example.Spring.LMS.entitys.ProgressEntity;
import com.example.Spring.LMS.entitys.CommentEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;
import java.util.List;

public record Users(
        @Null
        Long id,
        @NotNull
        String username,
        @NotNull
        @Email
        String email,
        @NotNull
        String password,
        @Null
        UserRole role,
        @NotNull
        @FutureOrPresent
        LocalDateTime dateCreated,
        List<CourseEntity> cours,
        List<EnrollmentEntity> enrollmentEntities,
        List<ProgressEntity> studentsProgressEntities,
        List<CommentEntity> commentEntities
) {
}
