package ru.ulstu.is.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    private Path saveUploadedFiles(MultipartFile file) throws IOException {
        Path path = Paths.get(String.format("%s%s%s.tmp",
                System.getProperty("java.io.tmpdir"),
                File.separator,
                System.currentTimeMillis()));
        Files.write(path, file.getBytes());
        return path;
    }

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        try {
            return saveUploadedFiles(file).toFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
