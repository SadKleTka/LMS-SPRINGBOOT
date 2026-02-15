package com.example.Spring.LMS.controllers;

import com.example.Spring.LMS.DTO.CourseResponse;
import com.example.Spring.LMS.services.CoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
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
}
