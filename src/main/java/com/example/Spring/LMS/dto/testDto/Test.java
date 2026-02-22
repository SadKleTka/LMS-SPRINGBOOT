package com.example.Spring.LMS.dto.testDto;

import com.example.Spring.LMS.dto.lessonDto.LessonResponse;
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
