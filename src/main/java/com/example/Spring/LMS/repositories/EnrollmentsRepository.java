package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.enrollments.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<EnrollmentEntity, Long> {
}
