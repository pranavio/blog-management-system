package com.example.blogkar.user.dto.response;


import com.example.blogkar.user.enums.Role;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "userId",
        "fullName",
        "email",
        "role"
})
public class UserResponse {

    private Integer userId;
    private String fullName;
    private String email;
    private Role role;
    public UserResponse(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


