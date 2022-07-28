package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.res.ItemResponse;
import com.example.karrotmarket.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberFacade memberFacade;

    public MyPageResponse my() {
        Member member = memberFacade.getCurrentUser();

        List<ItemResponse> memberItems = member.getItems().stream()
                .map(
                        item -> ItemResponse.builder()
                                .itemName(item.getItemName())
                                .itemDescription(item.getItemDescription())
                                .createdAt(item.getCreatedAt())
                                .canNego(item.isCanNegotiate())
                                .price(item.getPrice())
                                .itemStatus(item.getItemStatus())
                                .build()
                ).collect(Collectors.toList());
        return MyPageResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberAddress(member.getAddress())
                .memberItems(memberItems)
                .memberDealRequests(member.getDealRequests())
                .build();
    }
}
