package com.example.blogkar.post.controller;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page

    ){
        return ResponseEntity.ok(postService.getAllPosts(page, size));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable("postId") Integer postId,
            @RequestBody CreatePostRequest request){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable("postId") Integer postId
    ){
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully.");
    }
    @GetMapping("/search")
    public ResponseEntity<Page<PostResponse>> searchPosts(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

       return ResponseEntity.ok(postService.searchPosts(title, page, size));
    }
}