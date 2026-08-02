package com.example.blogkar.like.controller;

import com.example.blogkar.like.dto.LikeResponse;
import com.example.blogkar.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Like Management",
        description = "API's for Liking and Unliking blog posts."
)
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    @Operation(
            summary = "Like a post",
            description = "Allows the authenticated user to like a blog post."
    )
    @PostMapping("/{postId}/like")
    @PreAuthorize("hasAnyRole('USER','AUTHOR','ADMIN')")
    public ResponseEntity<LikeResponse> likePost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(
                likeService.likePost(postId)
        );
    }
    @Operation(
            summary = "Unlike a post",
            description = "Removes the authenticated user's like from a blog post."
    )
    @DeleteMapping("/{postId}/like")
    @PreAuthorize("hasAnyRole('USER','AUTHOR','ADMIN')")
    public ResponseEntity<LikeResponse> unlikePost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(
                likeService.unlikePost(postId)
        );
    }
}