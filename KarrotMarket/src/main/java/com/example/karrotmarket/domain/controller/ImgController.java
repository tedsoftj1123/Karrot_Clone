package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.service.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ImgController {

    private final ImgService imgService;
    @PostMapping("/upload-img/{itemId}")
    public void upload(@RequestPart("image") MultipartFile file, @PathVariable Long itemId) {
        imgService.storeImg(file, itemId);
    }

    @GetMapping("/download-img/{itemId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long itemId, HttpServletRequest request) {
        Resource resource = imgService.loadFileAsResource(itemId+".png");

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("a");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
