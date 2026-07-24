package com.example.blogkar.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

        @NotNull(message = "Post ID is required")
        private Integer postId;

        @NotBlank(message = "Message is required")
        private String message;

        // Getters and Setters
    }

