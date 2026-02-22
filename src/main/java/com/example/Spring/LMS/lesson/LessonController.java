package com.example.Spring.LMS.lesson;

import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.lesson.dto.LessonToCreate;
import com.example.Spring.LMS.lesson.dto.Lesson;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/course")
public class LessonController {

    private final static Logger log = LoggerFactory.getLogger(LessonController.class);

    private final LessonsService service;

    public LessonController(LessonsService service) {
        this.service = service;
    }

    @PostMapping("/{id}/lessons/{lessonId}/delete")
    public ResponseEntity<Lesson> deleteLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id") Long userId
    ) {
        log.info("Deleting lesson with id " + lessonId);

        service.deleteLessonById(id, lessonId, userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}/lessons/{lessonId}/edit")
    public ResponseEntity<LessonToCreate> editLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id") Long userId,
            @RequestBody
            @Valid
            LessonToCreate lesson
    ) {
        log.info("Edit lesson by id has been initiated");

        service.editLessonById(id, lessonId, userId, lesson);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping("/{id}/lessons/{lessonId}")
    public ResponseEntity<LessonResponse> getLessonById(
            @PathVariable Long id,
            @PathVariable Long lessonId,
            @RequestHeader("X-User-id") Long userId
    ) {
        log.info("Get lesson by id has been initiated");
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(service.getLessonById(id, lessonId, userId));

    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonResponse>> getAllLessons(
            @PathVariable
            Long id,
            @RequestHeader("X-User-id")
            Long userId
    ) {
        log.info("Getting all lessons for course");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllLessons(id, userId));
    }

    @PostMapping("/{id}/lessons/create")
    public ResponseEntity<LessonResponse> createLesson(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            LessonToCreate lesson,
            @RequestHeader("X-User-id")
            Long userId
    ) {
        log.info("Creating lesson for course");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createLesson(id, lesson, userId));
    }
}
