package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
