package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.HandleDealRequest;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/my")
    public MyPageResponse myPage() {
        return memberService.my();
    }
    @PatchMapping("/my/deal-requests")
    public void handleDealRequest(@RequestBody HandleDealRequest req) {
        memberService.handleDealRequest(req);
    }
}
