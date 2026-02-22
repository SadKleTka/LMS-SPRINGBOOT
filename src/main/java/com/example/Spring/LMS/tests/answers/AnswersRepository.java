package com.example.Spring.LMS.tests.answers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<AnswerOptionEntity, Long> {
}
