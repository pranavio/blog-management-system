package com.example.blogkar.user.mapper;

import com.example.blogkar.user.dto.response.UserProfileResponse;
import com.example.blogkar.user.dto.response.UserResponse;
import com.example.blogkar.user.entity.User;
import org.springframework.stereotype.Component;

import com.example.blogkar.user.dto.request.RegisterRequest;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest request){
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        return user;
    }
    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
    public UserProfileResponse toProfileResponse(User user) {

        UserProfileResponse response = new UserProfileResponse();

        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}
