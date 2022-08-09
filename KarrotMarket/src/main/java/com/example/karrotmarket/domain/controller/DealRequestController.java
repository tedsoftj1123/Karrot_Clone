package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.UserDealRequest;
import com.example.karrotmarket.domain.service.DealRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/main")
@RestController
public class DealRequestController {
    private final DealRequestService dealRequestService;

    @PostMapping("/{todoId}")
    public void sendDealRequest(@Valid  @PathVariable Long todoId, @RequestBody UserDealRequest req) {
        dealRequestService.sendDealRequest(todoId, req);
    }
}
