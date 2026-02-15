package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
}
