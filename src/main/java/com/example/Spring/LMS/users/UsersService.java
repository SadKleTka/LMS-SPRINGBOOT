package com.example.Spring.LMS.users;

import com.example.Spring.LMS.mapper.UserMapper;
import com.example.Spring.LMS.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;

    private final UserMapper mapper;

    public List<UserResponse> getAllUsers() {
        var users = repository.findAll();

        return users.stream().map(mapper::toResponse)
                .toList();
    }
}
