package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.dto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LMS/course/{id}/lessons/{lessonId}/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestsService service;

    @PostMapping("/questions/{questionId}/delete")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id") Long userId,
            @PathVariable Long questionId
    ) {
        log.info("Deleting question with id {} and lesson id {}", id, lessonId);

        service.deleteQuestion(id, lessonId, userId, questionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswersToAnswer> createAnswer(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id") Long userId,
            @PathVariable Long questionId,
            @Valid @RequestBody AnswersToAnswer answersToAnswer
            ) {
        log.info("Creating answer for lesson {}, question {}", lessonId, questionId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createAnswer(id, lessonId, userId, questionId, answersToAnswer));
    }

    @PostMapping("/questions")
    public ResponseEntity<QuestionToCreate> createQuestion(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestBody @Valid QuestionToCreate question,
            @RequestHeader("X-User-id") Long userId
            ) {
        log.info("Creating questions for lesson {}", lessonId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createQuestion(id, lessonId, question, userId));
    }

    @GetMapping
    public ResponseEntity<Test> getTest(
            @PathVariable Long lessonId
    ) {
        log.info("Test has been called");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getTest(lessonId));
    }

    @PostMapping("/delete")
    public ResponseEntity<Test> deleteTest(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id")
            Long userId
    ) {
        log.info("Test deletion has been called");

        service.deleteTest(id, lessonId, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<Test> createTest(
            @RequestBody
            @Valid
            TestToCreate testToCreate,
            @PathVariable
            Long id,
            @PathVariable
            Long lessonId,
            @RequestHeader("X-User-id")
            Long userId
    ) {
        log.info("Test creation has been called");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createTest(testToCreate, id, lessonId, userId));
    }
}
