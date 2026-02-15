package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.entitys.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
