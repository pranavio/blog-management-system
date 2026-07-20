package com.example.blogkar.user.mapper;

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
}
