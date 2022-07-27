package com.example.karrotmarket.controller;

import com.example.karrotmarket.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/my")
    public MyPageResponse myPage() {
        return memberService.my();
    }
}
