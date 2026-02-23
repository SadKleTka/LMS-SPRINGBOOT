package com.example.Spring.LMS.progresses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/LMS")
@RequiredArgsConstructor
@Slf4j
public class ProgressController {

    private final ProgressService service;

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
