package com.example.karrotmarket.domain.facade;

import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.ItemStatus;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.global.exception.NoAuthorityException;
import com.example.karrotmarket.global.exception.NonLoginException;
import com.example.karrotmarket.global.exception.UserNotFoundException;
import com.example.karrotmarket.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userId.equals("anonymousUser"))
            throw new NonLoginException();
        return memberRepository.findByMemberId(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public void validateUser(Item item, Member member) {
        if(item.getMember() != member) {
            throw new NoAuthorityException();
        }
    }

    public List<MyPageResponse.ItemResponse> findMemberItems(Member currentMember) {
        return currentMember.getItems().stream()
                .filter(i -> i.getItemStatus()!= ItemStatus.COMP)
                .map(
                item -> MyPageResponse.ItemResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .createdAt(item.getCreatedAt())
                        .canNego(item.isNegotiable())
                        .price(item.getPrice())
                        .itemStatus(item.getItemStatus())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<MyPageResponse.DealRequestResponse> findMemberDealRequests(Member currentMember) {
        return currentMember.getDealRequests()
                .stream().map(
                        d -> MyPageResponse.DealRequestResponse.builder()
                                .itemId(d.getItem().getId())
                                .itemOwnerId(d.getItemOwner())
                                .sendTime(d.getSendTime())
                                .price(d.getPrice())
                                .itemStatus(d.getItem().getItemStatus())
                                .locationDetail(d.getLocationDetail())
                                .timeDetail(d.getTimeDetail())
                                .build()
                ).collect(Collectors.toList());
    }
}
