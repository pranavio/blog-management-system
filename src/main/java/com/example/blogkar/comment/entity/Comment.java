package com.example.blogkar.comment.entity;

import com.example.blogkar.post.entity.Post;
import com.example.blogkar.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

//    @Column(name="user_id", nullable = false)
//    private Integer userId;
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "user_id", nullable = false)
      private User user;

//    @Column(name="post_id", nullable = false)
//    private Integer postId;
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "post_id", nullable = false)
      private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Column(name="message", nullable = false, columnDefinition = "TEXT")
    private String  message;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Comment(){

    }
    public Integer getCommentId() {
        return commentId;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
