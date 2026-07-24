package com.example.blogkar.comment.mapper;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {

        CommentResponse response = new CommentResponse();

        response.setCommentId(comment.getCommentId());
        response.setPostId(comment.getPost().getPostId());
        response.setAuthor(comment.getUser().getFullName());
        response.setMessage(comment.getMessage());
        response.setCreatedAt(comment.getCreatedAt());

        return response;
    }
}