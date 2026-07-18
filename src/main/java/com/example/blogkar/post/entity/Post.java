package com.example.blogkar.post.entity;

import com.example.blogkar.category.entity.Category;
import com.example.blogkar.comment.entity.Comment;
import com.example.blogkar.post.enums.Status;
import com.example.blogkar.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer postId;

    @Column(name="slug", nullable = false, unique = true)
    private  String slug;

    @Column(nullable = false)
    private  String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //  @Column(name="user_id" , nullable = false)
//  private Integer userId;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name="cover_image_url")
    private String coverImageUrl;

//    @Column(name="category_id", nullable = false)
//    private Integer categoryId;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    public Post(){

    }

    public Integer getPostId() {
        return postId;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }



    public String getContent() {
        return content;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
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


}
