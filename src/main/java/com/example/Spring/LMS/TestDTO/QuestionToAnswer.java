package com.example.Spring.LMS.TestDTO;

import java.util.List;

public record QuestionToAnswer(
        Long id,
        String text,
        List<AnswersToAnswer> answers
) {
}
