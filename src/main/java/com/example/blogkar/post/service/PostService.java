package com.example.blogkar.post.service;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import org.springframework.data.domain.Page;


public interface PostService {

    PostResponse createPost(CreatePostRequest request);
    public Page<PostResponse> getAllPosts(int size, int page);
}