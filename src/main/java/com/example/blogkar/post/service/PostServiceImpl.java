package com.example.blogkar.post.service;

import com.example.blogkar.category.entity.Category;
import com.example.blogkar.category.repository.CategoryRepository;
import com.example.blogkar.exception.ResourceNotFoundException;
import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.mapper.PostMapper;
import com.example.blogkar.post.repository.PostRepository;
import com.example.blogkar.user.entity.User;
import com.example.blogkar.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostResponse createPost(CreatePostRequest request) {

        User user = getAuthenticatedUser();

        Category category = getCategory(request.getCategoryId());

        String slug = generateUniqueSlug(request.getTitle());

        Post post = postMapper.toEntity(
                request,
                user,
                category,
                slug
        );

        Post savedPost = savePost(post);

        return postMapper.toResponse(savedPost);
    }

    private User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));
    }

    private Category getCategory(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));
    }

    private String generateUniqueSlug(String title) {

        String baseSlug = title
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");

        String slug = baseSlug;
        int count = 1;

        while (postRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + count;
            count++;
        }

        return slug;
    }

    private Post savePost(Post post) {
        return postRepository.save(post);
    }
}