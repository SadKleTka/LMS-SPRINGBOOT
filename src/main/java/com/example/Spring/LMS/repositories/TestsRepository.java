package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestsRepository extends JpaRepository<TestEntity, Long> {
}
