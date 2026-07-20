package com.example.blogkar.post.dto;

import com.example.blogkar.post.enums.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Integer postId;

    private String title;

    private String slug;

    private String content;

    private String coverImageUrl;

    private PostStatus status;

    private String authorName;

    private String categoryName;

    private LocalDateTime createdAt;
}