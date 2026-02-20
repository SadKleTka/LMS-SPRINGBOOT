package com.example.Spring.LMS.controllers;

import com.example.Spring.LMS.services.ProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/LMS")
public class ProgressController {

    private static final Logger log = LoggerFactory.getLogger(ProgressController.class);

    private final ProgressService service;

    public ProgressController(ProgressService service) {
        this.service = service;
    }

    @PostMapping("/lessons/{id}/complete")
    public ResponseEntity<?> completeLesson(
            @PathVariable Long id,
            @RequestHeader("X-User-id") Long userId
    ) {
        log.info("Received request to complete lesson with id {} for user with id {}", id, userId);

        service.completeLesson(id, userId);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping("/users/{id}/progress")
    public ResponseEntity<?> getProgress(
            @PathVariable Long id
    ){
        log.info("Getting progress for id {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getProgress(id));
    }



}
