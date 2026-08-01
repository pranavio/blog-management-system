package com.example.blogkar.like.controller;

import com.example.blogkar.like.dto.LikeResponse;
import com.example.blogkar.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    @PreAuthorize("hasAnyRole('USER','AUTHOR','ADMIN')")
    public ResponseEntity<LikeResponse> likePost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(
                likeService.likePost(postId)
        );
    }
    @DeleteMapping("/{postId}/like")
    @PreAuthorize("hasAnyRole('USER','AUTHOR','ADMIN')")
    public ResponseEntity<LikeResponse> unlikePost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(
                likeService.unlikePost(postId)
        );
    }
}