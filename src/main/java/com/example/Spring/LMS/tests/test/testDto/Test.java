package com.example.Spring.LMS.tests.test.testDto;

import com.example.Spring.LMS.lesson.lessonDto.LessonResponse;
import com.example.Spring.LMS.tests.questions.questionDto.QuestionToAnswer;
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
