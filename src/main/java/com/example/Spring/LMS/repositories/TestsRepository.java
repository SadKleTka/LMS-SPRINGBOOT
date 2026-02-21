package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entities.LessonEntity;
import com.example.Spring.LMS.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestsRepository extends JpaRepository<TestEntity, Long> {

    @Query(value = "delete from TestEntity c where c.lessons = :lessons")
    @Modifying
    void deleteTestByLesson(
            @Param("lessons") LessonEntity lessons
            );
}
