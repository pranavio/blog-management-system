package com.example.blogkar.like.service;

import com.example.blogkar.exception.AlreadyLikedException;
import com.example.blogkar.exception.LikeNotFoundException;
import com.example.blogkar.exception.ResourceNotFoundException;
import com.example.blogkar.like.dto.LikeResponse;
import com.example.blogkar.like.entity.Like;
import com.example.blogkar.like.repository.LikeRepository;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.repository.PostRepository;
import com.example.blogkar.security.CustomUserDetails;
import com.example.blogkar.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    @Override
    public LikeResponse unlikePost(Integer postId) {

        // Get logged-in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        // Find post
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found."));

        // Find existing like
        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() ->
                        new LikeNotFoundException("You have not liked this post."));

        // Delete like
        likeRepository.delete(like);

        // Count remaining likes
        long totalLikes = likeRepository.countByPost(post);

        // Return response
        return LikeResponse.builder()
                .message("Like removed successfully.")
                .totalLikes(totalLikes)
                .build();
    }

    @Override
    public LikeResponse likePost(Integer postId) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found."));
        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new AlreadyLikedException(
                    "You have already liked this post."
            );
        }

        // Create Like
        Like like = Like.builder()
                .user(user)
                .post(post)
                .createdAt(LocalDateTime.now())
                .build();

        // Save Like
        likeRepository.save(like);

        // Count total likes
        long totalLikes = likeRepository.countByPost(post);

        // Return response
        return LikeResponse.builder()
                .message("Post liked successfully.")
                .totalLikes(totalLikes)
                .build();
    }

}