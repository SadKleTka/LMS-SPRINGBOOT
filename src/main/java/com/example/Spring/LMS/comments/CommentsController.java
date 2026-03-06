package com.example.Spring.LMS.comments;

import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.comments.dto.CommentToCreate;
import com.example.Spring.LMS.comments.dto.CommentToResponse;
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
@RequestMapping("/LMS/course/{id}/comments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comments", description = "Comments actions")
public class CommentsController {

    private final CommentsService service;

    @AuthorizedUser
    @PostMapping("/create")
    public ResponseEntity<CommentToResponse> createComment(
            @PathVariable Long id,
            @RequestBody
            @Valid
            CommentToCreate comment,
            @AuthenticationPrincipal UsersEntity user
    ) {
        log.info("Received request to create comment");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(id, comment, user.getId()));
    }

    @AuthorizedUser
    @GetMapping
    public ResponseEntity<List<CommentToResponse>> getComments(
            @PathVariable Long id
    ) {
        log.info("get comments for the course");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getComments(id));
    }
}
