package com.example.blogkar.post.controller;

import com.example.blogkar.post.dto.CreatePostRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestBody @Valid CreatePostRequest request) {

        return ResponseEntity.ok("Post Created Successfully");
    }
}