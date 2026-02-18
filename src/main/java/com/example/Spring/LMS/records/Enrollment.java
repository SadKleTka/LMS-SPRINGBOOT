package com.example.Spring.LMS.records;

import com.example.Spring.LMS.CourseDTO.CourseResponse;
import com.example.Spring.LMS.DTO.UserResponse;
import com.example.Spring.LMS.entitys.CourseEntity;
import com.example.Spring.LMS.entitys.UsersEntity;
import com.example.Spring.LMS.repositories.UsersRepository;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record Enrollment(
        @Null
        Long id,
        @NotNull
        @FutureOrPresent
        LocalDateTime dateEnrollment,
        @NotNull
        UserResponse student,
        @NotNull
        CourseResponse course
) {
}
