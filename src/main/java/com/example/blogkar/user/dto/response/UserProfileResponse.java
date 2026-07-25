package com.example.blogkar.user.dto.response;

import com.example.blogkar.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileResponse {

    private Integer userId;

    private String fullName;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}