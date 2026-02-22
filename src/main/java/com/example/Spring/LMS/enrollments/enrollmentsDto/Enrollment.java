package com.example.Spring.LMS.enrollments.enrollmentsDto;

import com.example.Spring.LMS.course.courseDto.CourseResponse;
import com.example.Spring.LMS.users.usersDto.UserResponse;
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
