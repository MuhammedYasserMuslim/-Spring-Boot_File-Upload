package com.spring.services;

import com.spring.entity.Image;
import com.spring.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;


    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void insert(Image image) {
        imageRepository.save(image);
    }


}


