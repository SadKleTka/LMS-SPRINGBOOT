package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long> {
}
