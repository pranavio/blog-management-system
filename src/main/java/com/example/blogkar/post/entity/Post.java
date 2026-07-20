package com.example.blogkar.post.entity;

import com.example.blogkar.category.entity.Category;
import com.example.blogkar.comment.entity.Comment;
import com.example.blogkar.post.enums.PostStatus;
import com.example.blogkar.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    // ===========================
    // Primary Key
    // ===========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    // ===========================
    // Relationships
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    // ===========================
    // Business Fields
    // ===========================

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    // ===========================
    // Audit Fields
    // ===========================

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // ===========================
    // Constructors
    // ===========================

    public Post() {
    }

    // ===========================
    // Getters
    // ===========================

    public Integer getPostId() {
        return postId;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public PostStatus getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    // ===========================
    // Setters
    // ===========================

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}