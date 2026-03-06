package com.example.Spring.LMS.lesson;

import com.example.Spring.LMS.annotations.AdminOrTeacher;
import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.lesson.dto.LessonToCreate;
import com.example.Spring.LMS.users.UsersEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/course")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Lessons", description = "Lessons actions")
public class LessonController {

    private final LessonsService service;

    @AdminOrTeacher
    @DeleteMapping("/{id}/lessons/{lessonId}/delete")
    public ResponseEntity<?> deleteLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Deleting lesson with id " + lessonId);

        service.deleteLessonById(id, lessonId, user.getId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @AdminOrTeacher
    @PutMapping("/{id}/lessons/{lessonId}/edit")
    public ResponseEntity<LessonToCreate> editLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user,
            @RequestBody
            @Valid
            LessonToCreate lesson
    ) {
        log.info("Edit lesson by id has been initiated");

        service.editLessonById(id, lessonId, user.getId(), lesson);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @AuthorizedUser
    @GetMapping("/{id}/lessons/{lessonId}")
    public ResponseEntity<LessonResponse> getLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Get lesson by id has been initiated");
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(service.getLessonById(id, lessonId, user.getId()));

    }

    @AuthorizedUser
    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonResponse>> getAllLessons(
            @PathVariable
            Long id,
            @AuthenticationPrincipal
            UsersEntity user
    ) {
        log.info("Getting all lessons for course");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllLessons(id, user.getId()));
    }

    @AdminOrTeacher
    @PostMapping("/{id}/lessons/create")
    public ResponseEntity<LessonResponse> createLesson(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            LessonToCreate lesson,
            @AuthenticationPrincipal
            UsersEntity user
    ) {
        log.info("Creating lesson for course");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createLesson(id, lesson, user.getId()));
    }
}
