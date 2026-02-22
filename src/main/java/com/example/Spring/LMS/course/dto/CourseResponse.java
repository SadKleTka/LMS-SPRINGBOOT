package com.example.Spring.LMS.course.courseDto;


import com.example.Spring.LMS.users.usersDto.TeacherResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record CourseResponse(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String category,
        @NotNull
        String level,
        TeacherResponse teacher
) {

}
