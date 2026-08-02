package com.example.blogkar.post.controller;

import com.example.blogkar.post.dto.CreatePostRequest;
import com.example.blogkar.post.dto.PostResponse;
import com.example.blogkar.post.entity.Post;
import com.example.blogkar.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Post Management",
        description = "APIs for creating, updating, publishing, archiving, searching, and retrieving blog posts."
)
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("/my-drafts")
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    public ResponseEntity<Page<PostResponse>> getMyDraftPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                postService.getMyDraftPosts(page, size)
        );
    }
    @GetMapping("/my-published")
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    public ResponseEntity<Page<PostResponse>> getMyPublishedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                postService.getMyPublishedPosts(page, size)
        );
    }
    @GetMapping("my-archived")
    @PreAuthorize("hasAnyRole('AUTHOR', 'ADMIN')")
    public ResponseEntity<Page<PostResponse>> getMyArchivedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(
                postService.getMyArchivedPosts(page, size)
        );
    }
    @GetMapping("/my-posts")
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    public ResponseEntity<Page<PostResponse>> getMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(postService.getMyPosts(page, size));
    }
    @Operation(
            summary = "Archive a post",
            description = "Archives a published post."
    )
    @PutMapping("/{postId}/archive")
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public ResponseEntity<PostResponse> archivePost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(postService.archivePost(postId));
    }
    private final PostService postService;
    @Operation(
            summary = "Publish a post",
            description = "Changes a draft post to published status."
    )
    @PutMapping("/{postId}/publish")
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")
    public ResponseEntity<PostResponse> publishPost(
            @PathVariable Integer postId) {

        return ResponseEntity.ok(postService.publishPost(postId));
    }
    @Operation(
            summary = "Create a new post",
            description = "Creates a draft post for the authenticated author."
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody @Valid CreatePostRequest request) {

        PostResponse response = postService.createPost(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page

    ){
        return ResponseEntity.ok(postService.getAllPosts(page, size));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable("postId") Integer postId,
            @RequestBody CreatePostRequest request){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable("postId") Integer postId
    ){
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully.");
    }
    @GetMapping("/search")
    public ResponseEntity<Page<PostResponse>> searchPosts(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

       return ResponseEntity.ok(postService.searchPosts(title, page, size));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<PostResponse>> getPostsByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId, page,size));
    }
}