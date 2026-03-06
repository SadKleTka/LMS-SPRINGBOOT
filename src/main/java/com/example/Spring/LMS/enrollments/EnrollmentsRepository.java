package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentsRepository extends JpaRepository<EnrollmentEntity, Long> {

    boolean existsByStudentAndCourse(UsersEntity user, CourseEntity course);
}
