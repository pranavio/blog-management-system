package com.example.blogkar.auth.controlller;

import com.example.blogkar.auth.dto.AuthenticationResponse;
import com.example.blogkar.auth.dto.LoginRequest;
import com.example.blogkar.auth.dto.RegisterRequest;
import com.example.blogkar.auth.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication",
        description = "API for user registration and login"
)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @Operation(
            summary = "Register user",
            description = "Creates a new user account."
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @Operation(
            summary = "Login user",
            description = "Authenticates a user and returns a JWT access token."
    )
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}