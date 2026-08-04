package com.example.blogkar.auth.service;

import com.example.blogkar.auth.dto.AuthenticationResponse;
import com.example.blogkar.auth.dto.LoginRequest;
import com.example.blogkar.auth.dto.RegisterRequest;
import com.example.blogkar.exception.EmailAlreadyExistsException;
import com.example.blogkar.exception.InvalidCredentialsException;
import com.example.blogkar.security.JwtService;
import com.example.blogkar.user.entity.User;
import com.example.blogkar.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void shouldRegisterUserSuccessfully() {

        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Rahul Sharma");
        request.setEmail("rahul@gmail.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(passwordEncoder.encode(request.getPassword()))
                .thenReturn("encodedPassword");

        when(jwtService.generateToken(any(User.class)))
                .thenReturn("jwt-token");

        // Act
        AuthenticationResponse response =
                authenticationService.register(request);

        // Assert
        assertEquals("jwt-token", response.getToken());

        verify(userRepository).existsByEmail(request.getEmail());
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(any(User.class));
        verify(jwtService).generateToken(any(User.class));
    }
    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Rahul Sharma");
        request.setEmail("rahul@gmail.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(true);

        // Act & Assert
        assertThrows(
                EmailAlreadyExistsException.class,
                () -> authenticationService.register(request)
        );

        // Verify
        verify(userRepository).existsByEmail(request.getEmail());
        verify(userRepository, never()).save(any(User.class));
        verify(passwordEncoder, never()).encode(any());
        verify(jwtService, never()).generateToken(any(User.class));
    }
    @Test
    void shouldLoginSuccessfully() {

        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("rahul@gmail.com");
        request.setPassword("password123");

        User user = new User();
        user.setEmail("rahul@gmail.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        when(jwtService.generateToken(user))
                .thenReturn("jwt-token");

        // Act
        AuthenticationResponse response =
                authenticationService.login(request);

        // Assert
        assertEquals("jwt-token", response.getToken());

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtService).generateToken(user);
    }
    @Test
    void shouldThrowExceptionWhenLoginCredentialsAreInvalid() {

        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("rahul@gmail.com");
        request.setPassword("wrongPassword");

        doThrow(new BadCredentialsException("Bad credentials"))
                .when(authenticationManager)
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        // Act & Assert
        assertThrows(
                InvalidCredentialsException.class,
                () -> authenticationService.login(request)
        );

        // Verify
        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        verify(userRepository, never()).findByEmail(any());
        verify(jwtService, never()).generateToken(any(User.class));
    }
}