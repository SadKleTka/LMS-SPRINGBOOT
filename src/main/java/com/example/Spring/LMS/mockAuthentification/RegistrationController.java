package com.example.Spring.LMS.mockAuthentification;

import com.example.Spring.LMS.users.usersDto.UserResponse;
import com.example.Spring.LMS.users.usersDto.UserToCreate;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LMS")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }


    @PostMapping("/auth/registration")
    public ResponseEntity<UserToCreate> register(
            @RequestBody
            @Valid
            UserToCreate user
    ) {
        log.info("Registering user {}", user.username());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(user));

    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody
            @Valid
            UserToLogin user
    ) {
        log.info("Login user {}", user.username());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.login(user));
    }

}
