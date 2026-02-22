package com.example.Spring.LMS.users;

import com.example.Spring.LMS.users.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public List<UserResponse> getAllUsers() {
        var users = repository.findAll();

        return users.stream().map(this::toResponse)
                .toList();
    }


    private UserResponse toResponse(UsersEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
