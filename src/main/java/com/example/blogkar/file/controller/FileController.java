package com.example.blogkar.file.controller;

import com.example.blogkar.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String fileUrl = fileService.uploadFile(file);

        return ResponseEntity.ok(fileUrl);
    }
    @GetMapping("/test")
    public String test() {
        return "Working";
    }
}