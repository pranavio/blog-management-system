package com.example.blogkar.post.service;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;

public interface PostService {

    PostResponse createPost(CreatePostRequest request);

}