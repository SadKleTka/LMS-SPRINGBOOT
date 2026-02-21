package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entities.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressesRepository extends JpaRepository<ProgressEntity, Long> {
}
