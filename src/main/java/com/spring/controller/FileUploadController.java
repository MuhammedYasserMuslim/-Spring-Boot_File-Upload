package com.spring.controller;

import com.spring.services.FileUploadService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestParam Long id, @RequestParam String pathType, @RequestParam @Pattern(regexp = "^[\\S ]+\\.(jpg|jpeg|png|gif|bmp)$") MultipartFile file) {
        return fileUploadService.uploadFile(id, pathType, fileUploadService.convertMultipartFileToFile(file));
    }

    @PostMapping("/uploads")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> upload(@RequestParam Long id, @RequestParam String pathType, @RequestParam MultipartFile[] files) {
        List<String> multipartFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (java.util.regex.Pattern.matches("^[\\S ]+\\.(jpg|jpeg|png|gif|bmp)$", file.getOriginalFilename())) {
                upload(id, pathType, file);
                multipartFiles.add(file.getOriginalFilename());
            }
        }
        return multipartFiles;
    }

}
