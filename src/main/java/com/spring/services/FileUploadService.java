package com.spring.services;

import com.spring.entity.Image;
import com.spring.exception.exceptions.FileStorageException;
import com.spring.exception.exceptions.convertMultipartFileToFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileUploadService {

    private static final String BASE_PATH = "E:\\img\\";

    private final ImageService imageService;


    public String uploadFile(Long id, String pathType, File file) {
        Path fileStorageLocation = Path.of(BASE_PATH + pathType).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
        String fileName = StringUtils.cleanPath(id + "-" + file.getName());
        if (fileName.contains(".."))
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        try {
            Path targetLocation = fileStorageLocation.resolve(fileName);
            InputStream inputStream = new FileInputStream(file);
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            updateImagePath(id, pathType, file);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new convertMultipartFileToFileException(e.getMessage());
        }
        return file;
    }

    public void updateImagePath(Long id, String pathType, File file) {
        Image image = new Image(file.getName(),pathType + "\\" + id + "-" + file.getName());
        imageService.insert(image);
    }
}
