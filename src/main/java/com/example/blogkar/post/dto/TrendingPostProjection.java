package com.example.blogkar.post.dto;

import java.time.LocalDateTime;

public interface TrendingPostProjection {

    Integer getPostId();

    String getTitle();

    String getSlug();

    Integer getCategoryId();

    String getCategoryName();

    String getCoverImageUrl();

    LocalDateTime getCreatedAt();

    Long getLikeCount();
}