package com.example.Spring.LMS.DTO;


public record CourseResponse(
        String name,
        String description,
        String category,
        String level,
        TeacherResponse teacher
) {

}
