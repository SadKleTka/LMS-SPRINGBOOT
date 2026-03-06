package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.dto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.dto.Test;
import com.example.Spring.LMS.tests.test.dto.TestToCreate;

public interface TestsService {

    void deleteQuestion(Long id, Long lessonId, Long userId, Long questionId);

    AnswersToAnswer createAnswer(Long id, Long lessonId, Long userId, Long questionId, AnswersToAnswer answer);

    QuestionToCreate createQuestion(Long id, Long lessonId, QuestionToCreate question, Long userId);

    void deleteTest(Long id, Long lessonId, Long userId);

    Test createTest(TestToCreate testToCreate, Long id, Long lessonId, Long userId);

    Test getTest(Long id, Long lessonId, Long userId);
}