package com.example.loctest.service.strategy;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.file.storage.path}")
    private String fileStoragePath;


    public String storeFile(MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file");
            }

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!extension.equalsIgnoreCase("jpg") &&
                    !extension.equalsIgnoreCase("jpeg") &&
                    !extension.equalsIgnoreCase("pdf") &&
                    !extension.equalsIgnoreCase("word") &&
                    !extension.equalsIgnoreCase("txt") &&
                    !extension.equalsIgnoreCase("png")) {
                throw new IOException("Only JPG, JPEG, pdf, and PNG images are allowed");
            }

            String fileName = UUID.randomUUID().toString() + "." + extension;

            Path filePath = Paths.get(fileStoragePath + File.separator + fileName);
            Files.copy(file.getInputStream(), filePath);

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
