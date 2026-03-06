package com.example.Spring.LMS.progresses;

import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProgressesRepository extends JpaRepository<ProgressEntity, Long> {

    @Query("select c from ProgressEntity c where c.student = :student and c.status = c.status and c.lessons = :lesson")
    Optional<ProgressEntity> findByStudentAndStatusAndLesson(
            @Param("student") UsersEntity student,
            @Param("status") StatusOfProgress status,
            @Param("lesson") LessonEntity lesson);

    @Query(value = "update ProgressEntity c set c.status = :status where c.id = :id")
    @Modifying
    void finishLesson(
            @Param("id") Long id,
            @Param("status") StatusOfProgress status
    );
}
