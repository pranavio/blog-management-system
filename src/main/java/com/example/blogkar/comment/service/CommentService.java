package com.example.blogkar.comment.service;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.dto.CreateCommentRequest;

public interface CommentService {
    CommentResponse createComment(CreateCommentRequest request);
}
