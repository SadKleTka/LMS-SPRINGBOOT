package com.example.Spring.LMS.TestDTO;

import com.example.Spring.LMS.entitys.AnswerOptionEntity;

import java.util.List;

public record QuestionToAnswer(
        Long id,
        String text,
        List<AnswersToAnswer> answers
) {
}
