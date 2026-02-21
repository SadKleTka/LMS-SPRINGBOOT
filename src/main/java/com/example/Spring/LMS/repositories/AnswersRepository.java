package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entities.AnswerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<AnswerOptionEntity, Long> {
}
