package com.example.Spring.LMS.comments;

import com.example.Spring.LMS.comments.dto.CommentToCreate;
import com.example.Spring.LMS.comments.dto.CommentToResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/course/{id}/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {

    private final CommentsService service;

    @PostMapping("/create")
    public ResponseEntity<CommentToResponse> createComment(
            @PathVariable Long id,
            @RequestBody
            @Valid
            CommentToCreate comment,
            @RequestHeader("X-User-id") Long userId
    ) {
        log.info("Received request to create comment");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(id, comment, userId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<CommentToResponse>> getComments(
            @PathVariable Long id
    ) {
        log.info("get comments for the course");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getComments(id));
    }
}
