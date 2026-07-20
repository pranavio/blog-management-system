package com.example.blogkar.user.service;

import com.example.blogkar.exception.EmailAlreadyExistsException;
import com.example.blogkar.exception.InvalidCredentialsException;
import com.example.blogkar.security.JwtService;
import com.example.blogkar.user.dto.request.LoginRequest;
import com.example.blogkar.user.dto.request.RegisterRequest;
import com.example.blogkar.user.dto.response.LoginResponse;
import com.example.blogkar.user.dto.response.UserResponse;
import com.example.blogkar.user.entity.User;
import com.example.blogkar.user.enums.Role;
import com.example.blogkar.user.mapper.UserMapper;
import com.example.blogkar.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }
    public UserResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User saveUser = userRepository.save(user);
        UserResponse response = userMapper.toResponse(saveUser);
        return response;
    }
    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByEmail(request.email()).orElseThrow(()->
                new InvalidCredentialsException("Invalid email or password."));
        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )){
            throw new InvalidCredentialsException("Invalid email or password.");
        }
        String token = jwtService.generateToken(user);
        return new LoginResponse(
                token,
                "Bearer"
        );
    }
}
