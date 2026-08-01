package com.example.blogkar.like.repository;

import com.example.blogkar.like.entity.Like;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    boolean existsByUserAndPost(User user, Post post);

    Optional<Like> findByUserAndPost(User user, Post post);

    long countByPost(Post post);
}
