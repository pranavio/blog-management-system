package com.example.blogkar.like.service;

import com.example.blogkar.like.dto.LikeResponse;

public interface LikeService {

    LikeResponse likePost(Integer postId);
    LikeResponse unlikePost(Integer postId);

}