package com.demo.taskflow.attachment;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path root = Paths.get("uploads");

    public FileStorageService() throws IOException {
        Files.createDirectories(root);
    }

    public String save(MultipartFile file) throws IOException{
        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), root.resolve(storedName));
        return storedName;
    }

    public Resource load(String filename) {
        return new FileSystemResource(root.resolve(filename));

    }

    public void delete(String filename) throws IOException{
        Files.deleteIfExists(root.resolve(filename));

    }


}
