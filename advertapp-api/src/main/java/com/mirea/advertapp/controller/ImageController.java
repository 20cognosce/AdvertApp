package com.mirea.advertapp.controller;

import com.mirea.advertapp.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/images")
@RestController()
public class ImageController {

    private final ImageService imageService;

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String uploadImage(@RequestBody MultipartFile image,
                              @RequestParam("advert-id") Long advertId,
                              @RequestParam("title") String title) {
        return imageService.uploadImage(image, advertId, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") Long id) {
        var imageResource = imageService.downloadImage(id);
        return ResponseEntity.ok(imageResource);
    }
}
