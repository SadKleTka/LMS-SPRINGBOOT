package com.example.Spring.LMS.TestDTO;

import com.example.Spring.LMS.entitys.LessonEntity;

public record TestToCreate(
        Long id,
        String name,
        LessonEntity lesson
) {
}
