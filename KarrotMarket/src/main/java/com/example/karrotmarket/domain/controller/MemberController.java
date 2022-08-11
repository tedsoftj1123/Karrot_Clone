package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.ModifyItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.service.MemberService;
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
    } // 내가 보낸 거래요청조회

    @GetMapping("/my/deal-requests")
    public List<MyPageResponse.DealRequestResponse> inComingDealRequests() {
        return memberService.inComingDealRequests(); // 내가 받은 거래요청 조회
    }
    @PatchMapping("/my/deal-requests/{dealRequestId}")
    public MessageResponse acceptDealRequest(@PathVariable Long dealRequestId) {
        return memberService.acceptDealRequest(dealRequestId);
    }
    @DeleteMapping("/my/deal-requests/{dealRequestId}")
    public MessageResponse denyDealRequest(@PathVariable Long dealRequestId) {
        return memberService.denyDealRequest(dealRequestId);
    }

    @PatchMapping("/my/items/{itemId}")
    public MessageResponse turnUpItem(@PathVariable Long itemId) {
        return memberService.turnUpItem(itemId);
    }

    @PutMapping("/my/items/{itemId}")
    public MessageResponse modifyItem(@PathVariable Long itemId, @RequestBody ModifyItemRequest req) {
        return memberService.modifyItem(itemId, req);
    }
}
