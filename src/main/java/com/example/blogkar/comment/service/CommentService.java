package com.example.blogkar.comment.service;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.dto.CreateCommentRequest;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse createComment(CreateCommentRequest request);
    Page<CommentResponse> getCommentsByPost(
            Integer postId,
            int page,
            int size
    );
    CommentResponse updateComment(
            Integer commentId,
            CreateCommentRequest request
    );
    void deleteComment(Integer commentId);
}
