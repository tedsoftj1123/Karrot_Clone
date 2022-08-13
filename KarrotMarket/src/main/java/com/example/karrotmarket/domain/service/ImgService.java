package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.domain.img.FileUploadProperties;
import com.example.karrotmarket.domain.repository.ItemRepository;
import com.example.karrotmarket.global.exception.FileNotFoundException;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImgService {

    private final Path fileStoreLocation;
    private final MemberFacade memberFacade;
    private final ItemRepository itemRepository;

    @Autowired
    public ImgService(FileUploadProperties fileUploadProperties, MemberFacade memberFacade, ItemRepository itemRepository) {
        this.fileStoreLocation = Paths.get(fileUploadProperties.getFileUploadLocation())
                .toAbsolutePath().normalize();
        this.memberFacade = memberFacade;
        this.itemRepository = itemRepository;
        try {
            Files.createDirectories(this.fileStoreLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Transactional
    public void storeImg(MultipartFile file, Long itemId) {
        Member currentMember = memberFacade.getCurrentMember();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        String fileName = itemId+".png";
        memberFacade.validateUser(item, currentMember);
        item.setItemImgUrl(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/items/download-img")
                .path("/"+itemId)
                .toUriString());
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
