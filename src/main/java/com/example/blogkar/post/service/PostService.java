package com.example.blogkar.post.service;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface PostService {

    PostResponse createPost(CreatePostRequest request);
    Page<PostResponse> getAllPosts(int size, int page);
    PostResponse getPostById(Integer postId);
    PostResponse updatePost(Integer postId, CreatePostRequest request);
    void deletePost(Integer postId);
}