package com.example.Spring.LMS.controllers;

import com.example.Spring.LMS.CourseDTO.CourseResponse;
import com.example.Spring.LMS.records.Enrollment;
import com.example.Spring.LMS.services.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/users/{userId}/courses")
public class EnrollmentController {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentController.class);

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getStudentsCourses(
            @PathVariable Long userId
    ) {
        log.info("Getting students courses for user {}", userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getStudentsCourses(userId));
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<Enrollment> enrollToTheCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ) {
        log.info("Enrolling to the course {}", courseId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.enrollToTheCourse(userId, courseId));
    }

}
