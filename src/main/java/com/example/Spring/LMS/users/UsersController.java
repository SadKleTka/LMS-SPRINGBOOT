package com.example.Spring.LMS.users;

import com.example.Spring.LMS.users.dto.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/LMS/users")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Getting all users");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllUsers());
    }

}
