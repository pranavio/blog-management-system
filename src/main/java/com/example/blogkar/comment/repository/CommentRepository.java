package com.example.blogkar.comment.repository;

import com.example.blogkar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<User, Integer> {
}
