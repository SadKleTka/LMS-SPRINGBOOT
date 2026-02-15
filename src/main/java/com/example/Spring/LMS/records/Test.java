package com.example.Spring.LMS.records;

import com.example.Spring.LMS.entitys.LessonEntity;
import com.example.Spring.LMS.entitys.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Test(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        LessonEntity lessonEntity,
        List<QuestionEntity> questionEntities
) {
}
