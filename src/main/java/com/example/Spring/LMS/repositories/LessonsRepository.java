package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepository extends JpaRepository<LessonEntity, Long> {
}
