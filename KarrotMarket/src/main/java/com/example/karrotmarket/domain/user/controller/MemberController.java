package com.example.karrotmarket.domain.user.controller;

import com.example.karrotmarket.domain.user.controller.dto.request.HandleDealRequest;
import com.example.karrotmarket.domain.user.controller.dto.response.DealRequestResponse;
import com.example.karrotmarket.domain.user.controller.dto.response.ItemResponse;
import com.example.karrotmarket.domain.user.controller.dto.response.MyPageResponse;
import com.example.karrotmarket.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/my")
    public MyPageResponse myPage() {
        return memberService.my();
    }

    @GetMapping("/my/items")
    public List<ItemResponse> userItems() {
        return memberService.userItems();
    }

    @GetMapping("/my/deal-requests")
    public List<DealRequestResponse> userDealRequests() {
        return memberService.userDealRequests();
    }
    @PatchMapping("/my/deal-requests")
    public void handleDealRequest(@RequestBody HandleDealRequest req) {
        memberService.handleDealRequest(req);
    }
}
