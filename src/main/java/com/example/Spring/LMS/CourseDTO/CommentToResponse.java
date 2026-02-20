package com.example.Spring.LMS.CourseDTO;

import com.example.Spring.LMS.entitys.UsersEntity;

import java.time.LocalDateTime;

public record CommentToResponse(
        String name,
        String text,
        LocalDateTime time
) {
}
