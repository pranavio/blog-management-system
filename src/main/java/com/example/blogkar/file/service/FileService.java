package com.example.blogkar.file.service;

import com.example.blogkar.exception.InvalidFileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    public String uploadFile(MultipartFile file) {

        // 1. Check if file is empty
        if (file.isEmpty()) {
            throw new InvalidFileException("Please select a file to upload.");
        }

        // 2. Allow only image files
        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidFileException("Only image files are allowed.");
        }

        // 3. Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID() + "-" + originalFilename;

        // 4. Upload folder path
        Path uploadPath = Paths.get("uploads");

        // 5. Create uploads folder if it doesn't exist
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 6. Complete path of the file
            Path filePath = uploadPath.resolve(uniqueFileName);

            // 7. Save the file
            Files.copy(file.getInputStream(), filePath);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidFileException("Failed to upload file.");
        }

        // 8. Return file URL/path
        return "/uploads/" + uniqueFileName;
    }
}