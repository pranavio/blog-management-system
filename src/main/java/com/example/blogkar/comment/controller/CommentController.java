package com.example.blogkar.comment.controller;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.dto.CreateCommentRequest;
import com.example.blogkar.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid CreateCommentRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(request));
    }
}