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
import com.example.blogkar.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
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
    public CommentResponse updateComment(
            Integer commentId,
            CreateCommentRequest request) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User loggedInUser = userDetails.getUser();
        if (loggedInUser.getRole() != Role.ADMIN) {

            if (!loggedInUser.getUserId()
                    .equals(comment.getUser().getUserId())) {

                throw new AccessDeniedException(
                        "You are not allowed to update this comment");
            }
        }
        comment.setMessage(request.getMessage());

        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.toResponse(updatedComment);

    }
    @Override
    public Page<CommentResponse> getCommentsByPost(
            Integer postId,
            int page,
            int size) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments =
                commentRepository.findByPost_PostId(postId, pageable);
        return comments.map(commentMapper::toResponse);
    }
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
    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User loggedInUser = userDetails.getUser();

        // Admin can delete any comment
        if (loggedInUser.getRole() != Role.ADMIN) {

            // Owner can delete only their own comment
            if (!loggedInUser.getUserId()
                    .equals(comment.getUser().getUserId())) {

                throw new AccessDeniedException(
                        "You are not allowed to delete this comment");
            }
        }

        commentRepository.delete(comment);
    }

}
