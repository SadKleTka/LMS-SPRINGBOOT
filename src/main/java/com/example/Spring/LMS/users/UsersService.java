package com.example.Spring.LMS.users;

import com.example.Spring.LMS.users.dto.UserResponse;

import java.util.List;

public interface UsersService {

    List<UserResponse> getAllUsers();

    void deleteUserById(Long id);
}