package com.example.Spring.LMS.course;

import com.example.Spring.LMS.enums.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "update CourseEntity c set c.name = :name, c.description = :description, c.category = :category, c.level = :level where c.id = :id")
    @Modifying
    void updateCourse(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("category") String category,
            @Param("level")CourseLevel level
            );
}
