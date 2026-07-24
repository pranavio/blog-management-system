package com.example.blogkar.comment.service;

import com.example.blogkar.comment.dto.CommentResponse;
import com.example.blogkar.comment.mapper.CommentMapper;
import com.example.blogkar.comment.repository.CommentRepository;
import com.example.blogkar.comment.dto.CreateCommentRequest;
import com.example.blogkar.comment.entity.Comment;
import com.example.blogkar.exception.ResourceNotFoundException;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.repository.PostRepository;
import com.example.blogkar.security.CustomUserDetails;
import com.example.blogkar.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    @Override
    public CommentResponse createComment(CreateCommentRequest request) {

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User loggedInUser = userDetails.getUser();
        Comment comment = new Comment();
        comment.setUser(loggedInUser);
        comment.setPost(post);
        comment.setMessage(request.getMessage());
        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toResponse(savedComment);
    }

}
