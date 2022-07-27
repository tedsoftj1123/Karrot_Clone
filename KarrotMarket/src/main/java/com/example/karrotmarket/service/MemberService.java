package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberFacade memberFacade;

    public MyPageResponse my() {
        Member member = memberFacade.getCurrentUser();
        return MyPageResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberAddress(member.getAddress())
                .memberItems(member.getItems())
                .memberDealRequests(member.getDealRequests())
                .build();
    }
}
