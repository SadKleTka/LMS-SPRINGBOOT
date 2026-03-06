package com.example.Spring.LMS.authentification;

import com.example.Spring.LMS.authentification.dto.UserToCreate;
import com.example.Spring.LMS.users.dto.UserResponse;

public interface AuthenticationService {

    UserResponse register(UserToCreate user);

}
