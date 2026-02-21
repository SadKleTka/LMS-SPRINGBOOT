package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonsRepository extends JpaRepository<LessonEntity, Long> {

    @Query(value = "update LessonEntity c set c.name = :name, c.content = :content, c.videoUrl = :videoUrl where c.id = :id")
    @Modifying
    void updateLesson(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("content") String content,
            @Param("videoUrl") String videoUrl
    );

}
