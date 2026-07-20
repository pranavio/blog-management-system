package com.example.blogkar.post.controller;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import com.example.blogkar.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody @Valid CreatePostRequest request) {

        PostResponse response = postService.createPost(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}