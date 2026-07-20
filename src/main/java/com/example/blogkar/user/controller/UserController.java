package com.example.blogkar.user.controller;

import com.example.blogkar.user.dto.request.LoginRequest;
import com.example.blogkar.user.dto.request.RegisterRequest;
import com.example.blogkar.user.dto.response.LoginResponse;
import com.example.blogkar.user.dto.response.UserResponse;
import com.example.blogkar.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
        private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
           @Valid @RequestBody RegisterRequest request){

        UserResponse response = userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);

    }
}

