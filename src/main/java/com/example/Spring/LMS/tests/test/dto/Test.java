package com.example.Spring.LMS.tests.test.dto;

import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.tests.questions.dto.QuestionToAnswer;
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
