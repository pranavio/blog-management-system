package com.example.blogkar.user.dto.response;

import com.example.blogkar.user.enums.Role;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}
