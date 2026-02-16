package com.example.Spring.LMS.CourseDTO;


import com.example.Spring.LMS.DTO.TeacherResponse;

public record CourseResponse(
        Long id,
        String name,
        String description,
        String category,
        String level,
        TeacherResponse teacher
) {

}
