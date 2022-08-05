package com.example.karrotmarket.img;

import com.example.karrotmarket.global.exception.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImgService {

    private final Path fileStoreLocation;

    @Autowired
    public ImgService(FileUploadProperties fileUploadProperties) {
        System.out.println(fileUploadProperties.getFileUploadLocation());
        this.fileStoreLocation = Paths.get(fileUploadProperties.getFileUploadLocation())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStoreLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeImg(MultipartFile file, String fileName) {
        Path targetLocation = this.fileStoreLocation.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Cant store file");
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStoreLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("FileNotFound", ex);
        }
    }
}
