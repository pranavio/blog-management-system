package com.example.blogkar.post.mapper;

import com.example.blogkar.category.entity.Category;
import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.enums.PostStatus;
import com.example.blogkar.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(CreatePostRequest request, User user, Category category, String slug) {

        Post post = new Post();

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCoverImageUrl(request.getCoverImageUrl());

        post.setSlug(slug);

        post.setStatus(PostStatus.DRAFT);

        post.setUser(user);

        post.setCategory(category);

        return post;
    }

    public PostResponse toResponse(Post post) {

        PostResponse response = new PostResponse();

        response.setPostId(post.getPostId());
        response.setTitle(post.getTitle());
        response.setSlug(post.getSlug());
        response.setContent(post.getContent());
        response.setCoverImageUrl(post.getCoverImageUrl());
        response.setStatus(post.getStatus());

        response.setAuthorName(post.getUser().getFullName());

        response.setCategoryName(post.getCategory().getName());

        response.setCreatedAt(post.getCreatedAt());

        return response;
    }

}