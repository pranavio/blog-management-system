package com.example.blogkar.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {

    private Integer commentId;

    private Integer postId;

    private String author;

    private String message;

    private LocalDateTime createdAt;
}