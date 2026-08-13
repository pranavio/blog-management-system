package com.example.blogkar.category.service;

import com.example.blogkar.category.dto.CategoryResponse;
import com.example.blogkar.category.dto.CreateCategoryRequest;
import lombok.extern.java.Log;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Integer categoryId);

    CategoryResponse updateCategory(
            Integer categoryId,
            CreateCategoryRequest request
    );

    void deleteCategory(Integer categoryId);
}