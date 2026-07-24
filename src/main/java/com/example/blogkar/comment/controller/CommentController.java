package com.example.blogkar.comment.controller;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.dto.CreateCommentRequest;
import com.example.blogkar.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Integer commentId,
            @RequestBody @Valid CreateCommentRequest request) {

        return ResponseEntity.ok(
                commentService.updateComment(commentId, request)
        );
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<CommentResponse>> getCommentsByPost(
            @PathVariable Integer postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                commentService.getCommentsByPost(postId, page, size)
        );
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid CreateCommentRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(request));
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Integer commentId) {

        commentService.deleteComment(commentId);

        return ResponseEntity.ok("Comment deleted successfully");
    }
}