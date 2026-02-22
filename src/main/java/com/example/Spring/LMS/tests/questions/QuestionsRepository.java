package com.example.Spring.LMS.tests.questions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long> {
}
