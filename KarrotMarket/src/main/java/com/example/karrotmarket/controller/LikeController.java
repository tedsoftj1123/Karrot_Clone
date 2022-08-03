package com.example.karrotmarket.controller;

import com.example.karrotmarket.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final HeartService heartService;

    @GetMapping("/like/{todoId}")
    public void doLike(@PathVariable Long todoId) {
        heartService.doLike(todoId);
    }
}
