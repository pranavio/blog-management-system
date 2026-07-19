package com.example.blogkar.post.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {
        @NotBlank(message = "Title is Required.")
        @Size(min  = 5, max = 100, message = "Title must be between 5 and 100 character.")
        private String title;
        @NotBlank(message = "Content is required.")
        @Valid
        private String content;
        @NotNull(message = "Category is required.")
        private Integer  categoryId;
        private String coverImageUrl;
    public CreatePostRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
