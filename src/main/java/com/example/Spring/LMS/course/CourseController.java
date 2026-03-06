package com.example.Spring.LMS.course;

import com.example.Spring.LMS.annotations.AdminOrTeacher;
import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;
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
@Tag(name = "Courses", description = "Courses actions")
public class CourseController {

    private final CoursesService service;

    @AuthorizedUser
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        log.info("All courses has been initiated");
        return ResponseEntity.ok(service.getAllCourses());
    }

    @AuthorizedUser
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id
    ) {
        log.info("Get course by id has been initiated");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getCourseById(id));

    }

    @AdminOrTeacher
    @PostMapping("/create")
    public ResponseEntity<CourseToCreate> createCourse(
            @AuthenticationPrincipal UsersEntity user,
            @RequestBody
            @Valid
            CourseToCreate course
    ) {
        log.info("Create course has been initiated");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCourse(user.getId(), course));
    }

    @AdminOrTeacher
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponse> deleteCourseById(
            @PathVariable Long id,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Delete course by id has been initiated");

        service.deleteCourseById(id, user.getId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @AdminOrTeacher
    @PutMapping("/{id}/edit")
    public ResponseEntity<CourseToCreate> editCourseById(
            @PathVariable Long id,
            @AuthenticationPrincipal UsersEntity user,
            @RequestBody
            @Valid
            CourseToCreate course
    ) {
        log.info("Edit course by id has been initiated");

        service.editCourseById(id, user.getId(), course);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
