package com.example.Spring.LMS.TestDTO;

import com.example.Spring.LMS.LessonDTO.LessonResponse;
import com.example.Spring.LMS.entitys.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Test(
        @NotNull
        String name,
        @NotNull
        LessonResponse lessons,
        List<QuestionToAnswer> questions
) {
}
