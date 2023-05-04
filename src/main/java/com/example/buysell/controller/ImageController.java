package com.example.buysell.controller;

import com.example.buysell.model.Image;
import com.example.buysell.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    private ResponseEntity<?>getImageById(@PathVariable Long id){
        Image image =imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok().header("fileName",image.getOriginalFilesName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
