package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.annotations.AdminOrTeacher;
import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.dto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.dto.*;
import com.example.Spring.LMS.users.UsersEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LMS/course/{id}/lessons/{lessonId}/test")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "TestController", description = "Test and linked actions")
public class TestController {

    private final TestsService service;

    @AdminOrTeacher
    @DeleteMapping("/questions/{questionId}/delete")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user,
            @PathVariable Long questionId
    ) {
        log.info("Deleting question with id {} and lesson id {}", id, lessonId);

        service.deleteQuestion(id, lessonId, user.getId(), questionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @AdminOrTeacher
    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswersToAnswer> createAnswer(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user,
            @PathVariable Long questionId,
            @Valid @RequestBody AnswersToAnswer answersToAnswer
            ) {
        log.info("Creating answer for lesson {}, question {}", lessonId, questionId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createAnswer(id, lessonId, user.getId(), questionId, answersToAnswer));
    }

    @AdminOrTeacher
    @PostMapping("/questions")
    public ResponseEntity<QuestionToCreate> createQuestion(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestBody @Valid QuestionToCreate question,
            @AuthenticationPrincipal UsersEntity user
            ) {
        log.info("Creating questions for lesson {}", lessonId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createQuestion(id, lessonId, question, user.getId()));
    }

    @AuthorizedUser
    @GetMapping
    public ResponseEntity<Test> getTest(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Test has been called");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getTest(id, lessonId, user.getId()));
    }

    @AdminOrTeacher
    @DeleteMapping("/delete")
    public ResponseEntity<Test> deleteTest(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Test deletion has been called");

        service.deleteTest(id, lessonId, user.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @AdminOrTeacher
    @PostMapping("/create")
    public ResponseEntity<Test> createTest(
            @RequestBody
            @Valid
            TestToCreate testToCreate,
            @PathVariable
            Long id,
            @PathVariable
            Long lessonId,
            @AuthenticationPrincipal
            UsersEntity user
    ) {
        log.info("Test creation has been called");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createTest(testToCreate, id, lessonId, user.getId()));
    }
}
