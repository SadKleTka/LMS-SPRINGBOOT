package com.example.Spring.LMS.course;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/course")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CoursesService service;

    public CourseController(CoursesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        log.info("All courses has been initiated");
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id
    ) {
        log.info("Get course by id has been initiated");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getCourseById(id));

    }

    @PostMapping("/create")
    public ResponseEntity<CourseToCreate> createCourse(
            @RequestHeader("X-User-id") Long userId,
            @RequestBody
            @Valid
            CourseToCreate course
    ) {
        log.info("Create course has been initiated");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCourse(userId, course));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<CourseResponse> deleteCourseById(
            @PathVariable Long id,
            @RequestHeader("X-User-id") Long userId
    ) {
        log.info("Delete course by id has been initiated");

        service.deleteCourseById(id, userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<CourseToCreate> editCourseById(
            @PathVariable Long id,
            @RequestHeader("X-User-id") Long userId,
            @RequestBody
            @Valid
            CourseToCreate course
    ) {
        log.info("Edit course by id has been initiated");

        service.editCourseById(id, userId, course);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
