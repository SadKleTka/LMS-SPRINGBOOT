package com.example.Spring.LMS.comments;

import com.example.Spring.LMS.comments.dto.CommentToCreate;
import com.example.Spring.LMS.comments.dto.CommentToResponse;

import java.util.List;

public interface CommentsService {

    CommentToResponse createComment(Long id, CommentToCreate comment, Long userId);

    List<CommentToResponse> getComments(Long id);
}
