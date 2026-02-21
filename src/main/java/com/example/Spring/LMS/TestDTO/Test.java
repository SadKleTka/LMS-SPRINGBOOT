package com.example.Spring.LMS.TestDTO;

import com.example.Spring.LMS.LessonDTO.LessonResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Test(
        @NotNull
        String name,
        @NotNull
        LessonResponse lessons,
        List<QuestionToAnswer> questions
) {
}
