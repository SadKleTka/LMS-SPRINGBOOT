package com.example.Spring.LMS.repositories;

import com.example.Spring.LMS.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query(value = "select * from users r where r.username = :username", nativeQuery = true)
    Optional<UsersEntity> findByUsername(
            @Param("username") String username
    );


}
