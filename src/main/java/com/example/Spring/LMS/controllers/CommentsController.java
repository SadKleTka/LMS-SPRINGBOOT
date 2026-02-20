package com.example.Spring.LMS.controllers;

import com.example.Spring.LMS.CourseDTO.CommentToCreate;
import com.example.Spring.LMS.CourseDTO.CommentToResponse;
import com.example.Spring.LMS.records.Comment;
import com.example.Spring.LMS.services.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/course/{id}/comments")
public class CommentsController {

    private static final Logger log = LoggerFactory.getLogger(CommentsController.class);

    private final CommentsService service;

    public CommentsController(CommentsService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentToResponse> createComment(
            @PathVariable Long id,
            @RequestBody CommentToCreate comment,
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
