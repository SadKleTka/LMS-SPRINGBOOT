package com.example.Spring.LMS.controllers;

import com.example.Spring.LMS.dto.testDto.*;
import com.example.Spring.LMS.services.TestsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LMS/course/{id}/lessons/{lessonId}/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private final TestsService service;

    public TestController(TestsService service) {
        this.service = service;
    }

    @PostMapping("/questions/{questionId}/delete")
    public ResponseEntity<Question> deleteQuestion(
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
