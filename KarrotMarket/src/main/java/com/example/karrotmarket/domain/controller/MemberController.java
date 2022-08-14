package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.req.ModifyItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.InComingDealRequestResponse;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public MyPageResponse myPage() {
        return memberService.my();
    } // 내가 보낸 거래요청조회

    @GetMapping("/deal-requests")
    public List<InComingDealRequestResponse> inComingDealRequests() {
        return memberService.inComingDealRequests(); // 내가 받은 거래요청 조회
    }
    @PatchMapping("/deal-requests/{dealRequestId}")
    public MessageResponse acceptDealRequest(@PathVariable Long dealRequestId) {
        return memberService.acceptDealRequest(dealRequestId);
    }
    @DeleteMapping("/deal-requests/{dealRequestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse denyDealRequest(@PathVariable Long dealRequestId) {
        return memberService.denyDealRequest(dealRequestId);
    }

    @PatchMapping("/items/{itemId}")
    public MessageResponse turnUpItem(@PathVariable Long itemId) {
        return memberService.turnUpItem(itemId);
    }

    @GetMapping("/hearts")
    public List<ShowAllItemsResponse> bookMark() {
        return memberService.bookMark();
    }

    @PutMapping("/items/{itemId}")
    public MessageResponse modifyItem(@PathVariable Long itemId, @RequestBody ModifyItemRequest req) {
        return memberService.modifyItem(itemId, req);
    }

    @GetMapping("/items/comp")
    public List<ShowAllItemsResponse> completedItems() {
        return memberService.completedItems();
    }

    @PatchMapping("/items/comp/{itemId}")
    public MessageResponse completeDeal(@PathVariable Long itemId) {
        return memberService.completeDeal(itemId);
    }
}
