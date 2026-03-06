package com.example.Spring.LMS.authentification;

import com.example.Spring.LMS.authentification.dto.UserToCreate;
import com.example.Spring.LMS.authentification.dto.UserToLogin;
import com.example.Spring.LMS.security.JwtService;
import com.example.Spring.LMS.users.impl.UserDetailsImpl;
import com.example.Spring.LMS.users.UsersEntity;
import com.example.Spring.LMS.users.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/LMS/auth")
@Slf4j
@Tag(name = "Auth", description = "Authentication/Authorization")
public class AuthenticationController {

    private final AuthenticationService service;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserDetailsImpl usersDetailsImpl;

    @Operation(summary = "Login", security = {})
    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody
            @Valid
            UserToLogin request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UsersEntity user = (UsersEntity) usersDetailsImpl.loadUserByUsername(request.email());

        String token = jwtService.generateToken(user);

        return Map.of("token", token);
    }

    @Operation(summary = "Registration", security = {})
    @PostMapping("/registration")
    public ResponseEntity<UserResponse> register(
            @RequestBody
            @Valid
            UserToCreate user
    ) {
        log.info("Registering user {}", user.email());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(user));

    }

}
