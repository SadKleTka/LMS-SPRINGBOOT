package com.example.Spring.LMS.users.impl;

import com.example.Spring.LMS.mapper.UserMapper;
import com.example.Spring.LMS.users.UsersRepository;
import com.example.Spring.LMS.users.UsersService;
import com.example.Spring.LMS.users.dto.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;

    private final UserMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        var users = repository.findAll();

        return users.stream().map(mapper::toResponse)
                .toList();
    }

    @Override
    public void deleteUserById(Long id) {
        var user = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User with id " + id + " not found"));

        repository.delete(user);
    }
}
