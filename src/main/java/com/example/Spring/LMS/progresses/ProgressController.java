package com.example.Spring.LMS.progresses;

import com.example.Spring.LMS.annotations.StudentOrAdmin;
import com.example.Spring.LMS.users.UsersEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/LMS")
@RequiredArgsConstructor
@Slf4j
public class ProgressController {

    private final ProgressService service;

    @StudentOrAdmin
    @PostMapping("/lessons/{id}/complete")
    public ResponseEntity<?> completeLesson(
            @PathVariable Long id,
            @AuthenticationPrincipal UsersEntity user
            ) {
        log.info("Received request to complete lesson with id {} for user with id {}", id, user.getId());

        service.completeLesson(id, user.getId());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @StudentOrAdmin
    @GetMapping("/users/progress")
    public ResponseEntity<?> getProgress(
            @AuthenticationPrincipal UsersEntity user
    ){
        log.info("Getting progress for id {}", user.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getProgress(user.getId()));
    }



}
