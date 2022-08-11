package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final HeartService heartService;

    @PatchMapping("/item/like/{itemId}")
    public MessageResponse doLike(@PathVariable Long itemId) {
        return heartService.doLike(itemId);
    }
}
