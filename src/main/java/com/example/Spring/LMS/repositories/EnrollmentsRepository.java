package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<EnrollmentEntity, Long> {
}
