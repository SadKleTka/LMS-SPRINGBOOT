package com.example.Spring.LMS.users;

import com.example.Spring.LMS.annotations.AuthorizedUser;
import com.example.Spring.LMS.users.dto.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LMS/users")
@Slf4j
@Tag(name = "UserController", description = "Actions with users")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @AuthorizedUser
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Getting all users");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(
            @PathVariable Long id
    ) {
        log.info("Deleting user {}", id);

        service.deleteUserById(id);
    }

}
