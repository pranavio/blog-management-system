package com.example.blogkar.user.service;

import com.example.blogkar.exception.EmailAlreadyExistsException;
import com.example.blogkar.exception.InvalidCredentialsException;
import com.example.blogkar.exception.PasswordMismatchException;
import com.example.blogkar.security.CustomUserDetails;
import com.example.blogkar.security.JwtService;
import com.example.blogkar.user.dto.request.ChangePasswordRequest;
import com.example.blogkar.user.dto.request.LoginRequest;
import com.example.blogkar.user.dto.request.RegisterRequest;
import com.example.blogkar.user.dto.request.UpdateProfileRequest;
import com.example.blogkar.user.dto.response.LoginResponse;
import com.example.blogkar.user.dto.response.UserProfileResponse;
import com.example.blogkar.user.dto.response.UserResponse;
import com.example.blogkar.user.entity.User;
import com.example.blogkar.user.enums.Role;
import com.example.blogkar.user.mapper.UserMapper;
import com.example.blogkar.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public UserProfileResponse getMyProfile(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        return userMapper.toProfileResponse(user);
    }
    public UserProfileResponse updateProfile(UpdateProfileRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {

            throw new EmailAlreadyExistsException("Email already exists.");
        }
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        User updatedUser = userRepository.save(user);
        return userMapper.toProfileResponse(updatedUser);
    }
    public String changePassword(ChangePasswordRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();
        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Current password is incorrect.");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("New password and confirm password do not match.");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        return "Password changed successfully.";
    }
}
