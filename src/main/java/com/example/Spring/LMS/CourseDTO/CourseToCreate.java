package com.example.Spring.LMS.CourseDTO;

import com.example.Spring.LMS.enums.CourseLevel;

public record CourseToCreate(
        String name,
        String description,
        String category,
        CourseLevel level
) {
}
