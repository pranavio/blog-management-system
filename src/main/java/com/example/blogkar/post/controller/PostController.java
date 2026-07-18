package com.example.blogkar.post.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    @GetMapping
    public String getAllProducts() {
        return "here you can get your all post";
    }

}
