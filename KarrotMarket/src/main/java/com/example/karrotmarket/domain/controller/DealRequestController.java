package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.UserDealRequest;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.service.DealRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/items/deal-request")
@RestController
public class DealRequestController {
    private final DealRequestService dealRequestService;

    @PostMapping("/{itemId}")
    public MessageResponse sendDealRequest(@PathVariable Long itemId, @Valid @RequestBody UserDealRequest req) {
        return dealRequestService.sendDealRequest(itemId, req);
    }
}
