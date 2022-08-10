package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.HandleDealRequest;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.service.MemberService;
import lombok.Getter;
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

    @GetMapping("/my/deal-requests")
    public List<MyPageResponse.DealRequestResponse> inComingDealRequests() {
        return memberService.inComingDealRequests();
    }
    @PatchMapping("/my/deal-requests/{dealRequestId}")
    public MessageResponse acceptDealRequest(@PathVariable Long dealRequestId) {
        return memberService.acceptDealRequest(dealRequestId);
    }
    @DeleteMapping("/my/deal-requests/{dealRequestId}")
    public MessageResponse denyDealRequest(@PathVariable Long dealRequestId) {
        return memberService.denyDealRequest(dealRequestId);
    }
}
