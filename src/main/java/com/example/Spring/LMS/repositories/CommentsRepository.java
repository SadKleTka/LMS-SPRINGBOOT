package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.CommentEntity;
import com.example.Spring.LMS.entitys.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "select c from CommentEntity c where c.course = :course")
    List<CommentEntity> findAllComments(
            @Param("course") CourseEntity course
    );
}
