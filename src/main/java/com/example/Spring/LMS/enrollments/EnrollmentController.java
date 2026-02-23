package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.enrollments.dto.Enrollment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/users/{userId}/courses")
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController {

    private final EnrollmentService service;

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
