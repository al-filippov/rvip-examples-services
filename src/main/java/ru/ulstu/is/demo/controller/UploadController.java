package ru.ulstu.is.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ulstu.is.demo.response.Response;
import ru.ulstu.is.demo.service.UploadService;

@RestController
@RequestMapping("/api/1.0/upload")
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/")
    public Response<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return new Response<>(uploadService.uploadFile(file));
    }
}
