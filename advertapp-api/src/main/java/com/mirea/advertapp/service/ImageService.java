package com.mirea.advertapp.service;

import com.mirea.advertapp.controller.exception.EntityNotFoundException;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.domain.entity.Image;
import com.mirea.advertapp.repo.ImageRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final AdvertService advertService;
    private final Path imageStorageDir;

    @Autowired
    public ImageService(@Value("${image-storage-dir}") Path imageStorageDir,
                        ImageRepository imageRepository,
                        AdvertService advertService) {
        this.imageStorageDir = imageStorageDir;
        this.imageRepository = imageRepository;
        this.advertService = advertService;
    }

    @PostConstruct
    public void ensureDirectoryExists() throws IOException {
        if (!Files.exists(this.imageStorageDir)) {
            Files.createDirectories(this.imageStorageDir);
        }
    }

    public String uploadImage(MultipartFile image, Long advertId, String title) throws IllegalArgumentException {
        Advert advert = advertService.getById(advertId);

        if (!verifyImage(image)) {
            throw new IllegalArgumentException("File extension is not supported");
        }

        Image imageEntity = saveImageToFileSystem(image, advert)
                .orElseThrow(() -> new IllegalArgumentException("Failed to save image"));
        imageEntity.setTitle(title);
        imageEntity.setAdvert(advert);

        imageRepository.save(imageEntity);
        return imageEntity.getPath();
    }

    public Resource downloadImage(@PathVariable("id") Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id = " + id + " not found"));

        File foundFile = new File(image.getPath());
        return new PathResource(foundFile.getPath());
    }

    private boolean verifyImage(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        String extensionType = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf('.') + 1);

        return "jpg".equals(extensionType) || "jpeg".equals(extensionType) || "png".equals(extensionType);
    }

    private Optional<Image> saveImageToFileSystem(MultipartFile image, Advert advert) {
        File folder = new File(imageStorageDir + "/" + advert.getId());
        folder.mkdir();

        String targetFileName = image.getOriginalFilename();
        Path targetPath = folder.toPath().resolve(Objects.requireNonNull(targetFileName));

        try (InputStream in = image.getInputStream()) {
            try (OutputStream out = Files.newOutputStream(targetPath, StandardOpenOption.CREATE)) {
                in.transferTo(out);
            }
        } catch (IOException e) {
            return Optional.empty();
        }

        return Optional.of(
                Image.builder()
                .path(targetPath.toString())
                .build());
    }
}
