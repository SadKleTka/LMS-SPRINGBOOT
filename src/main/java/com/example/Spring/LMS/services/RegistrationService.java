package com.example.Spring.LMS.services;

import com.example.Spring.LMS.dto.UserResponse;
import com.example.Spring.LMS.dto.UserToCreate;
import com.example.Spring.LMS.dto.UserToLogin;
import com.example.Spring.LMS.entities.UsersEntity;
import jakarta.persistence.EntityNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import com.example.Spring.LMS.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class RegistrationService {

    private final UsersRepository repository;

    public RegistrationService(UsersRepository repository) {
        this.repository = repository;
    }

    public UserResponse login(UserToLogin user) {
        var find = repository.findByUsername(user.username()).orElseThrow(()
                -> new EntityNotFoundException("User not found"));
        if (!BCrypt.checkpw(user.password(), find.getPassword())) {
            throw new SecurityException("Incorrect password");
        }

        return toGetUserResponse(find);
    }

    public UserToCreate register(UserToCreate user) {
        if (user.id() != null) {
            throw new IllegalStateException("You can`t place your id");
        }
        boolean hasUpper = user.password().matches(".*[A-Z].*");
        boolean hasLower = user.password().matches(".*[a-z].*");
        boolean hasDigit = user.password().matches(".*\\d.*");
        boolean hasSpecial = user.password().matches(".*[!@#$%^&*()].*");

        if (!hasUpper || !hasLower || !hasDigit || !hasSpecial) {
            throw new SecurityException("""
                    A password must contain at least one of each:
                    lowercase letter
                    uppercase letter
                    number
                    special character""");
        }
        String hashedPassword = BCrypt.hashpw(user.password(), BCrypt.gensalt());

        var newUser = new UsersEntity(
                null,
                user.username(),
                user.email(),
                hashedPassword,
                user.role()
        );
        newUser.setDateCreated(LocalDateTime.now());

        var saved = repository.save(newUser);

        return toCreateUser(saved);
    }

    private UserResponse toGetUserResponse(UsersEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    private UserToCreate toCreateUser(UsersEntity user) {
        return new UserToCreate(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
