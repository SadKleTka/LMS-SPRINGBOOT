package com.example.Spring.LMS.authentification;

import com.example.Spring.LMS.authentification.dto.UserToCreate;
import com.example.Spring.LMS.mapper.UserMapper;
import com.example.Spring.LMS.users.dto.UserResponse;
import com.example.Spring.LMS.users.UsersEntity;
import lombok.RequiredArgsConstructor;
import com.example.Spring.LMS.users.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserToCreate user) {

        if (repository.findByEmail(user.email()).isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        }

        validatePassword(user.password());

        var newUser = UsersEntity.builder()
                .username(user.username())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .role(user.role())
                .build();

        newUser.setDateCreated(LocalDateTime.now());

        var saved = repository.save(newUser);
        return mapper.toResponse(saved);
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");

        if (!hasUpper || !hasLower || !hasDigit || !hasSpecial) {
            throw new IllegalArgumentException("""
                    Password must contain every each of those:
                    lowercase letter,
                    uppercase letter,
                    number,
                    special character.
                    """);
        }
    }
}
