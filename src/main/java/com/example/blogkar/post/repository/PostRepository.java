package com.example.blogkar.post.repository;

import com.example.blogkar.post.entity.Post;
import com.example.blogkar.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer
        > {
    boolean existsBySlug(String slug);
    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Post> findByCategory_CategoryId(Integer categoryId, Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);
}
