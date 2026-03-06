package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.annotations.StudentOrAdmin;
import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.enrollments.dto.Enrollment;
import com.example.Spring.LMS.users.UsersEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/enrollment/courses")
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController {

    private final EnrollmentService service;

    @AuthorizedUser
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getStudentsCourses(
            @AuthenticationPrincipal UsersEntity user
            ) {
        log.info("Getting students courses for user {}", user.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getStudentsCourses(user.getId()));
    }

    @StudentOrAdmin
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<Enrollment> enrollToTheCourse(
            @AuthenticationPrincipal UsersEntity user,
            @PathVariable Long courseId
    ) {
        log.info("Enrolling to the course {}", courseId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.enrollToTheCourse(user.getId(), courseId));
    }

}
