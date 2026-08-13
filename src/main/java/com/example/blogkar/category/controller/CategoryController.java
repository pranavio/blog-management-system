package com.example.blogkar.category.controller;

import com.example.blogkar.category.dto.CategoryResponse;
import com.example.blogkar.category.dto.CreateCategoryRequest;
import com.example.blogkar.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(
        name = "Category Management",
        description = "APIs for managing blog categories"
)
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create category",
            description = "Creates a new blog category."
    )
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.createCategory(request));
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Returns all available blog categories."
    )
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }

    @GetMapping("/{categoryId}")
    @Operation(
            summary = "Get category by ID",
            description = "Returns a category using its ID."
    )
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable Integer categoryId) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(categoryId)
        );
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update category",
            description = "Updates an existing blog category."
    )
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody @Valid CreateCategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.updateCategory(categoryId, request)
        );
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete category",
            description = "Deletes an existing blog category."
    )
    public ResponseEntity<String> deleteCategory(
            @PathVariable Integer categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok(
                "Category deleted successfully."
        );
    }
}