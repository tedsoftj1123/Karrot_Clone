package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.req.HandleDealRequest;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.entity.DealRequest;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.ItemStatus;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.global.exception.DealRequestNotFound;
import com.example.karrotmarket.domain.repository.DealRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberFacade memberFacade;
    private final DealRequestRepository dealRequestRepository;

    public MyPageResponse my() {
        Member member = memberFacade.getCurrentUser();
        List<MyPageResponse.DealRequestResponse> acceptedDealRequests = member.getDealRequests()
                .stream().filter(DealRequest::isAccepted)
                .map(d -> MyPageResponse.DealRequestResponse.builder()
                        .dealMember(d.getDealMember())
                        .price(d.getPrice())
                        .day(d.getDay())
                        .location(d.getLocation())
                        .build())
                .collect(Collectors.toList());
        List<MyPageResponse.ItemResponse> memberItems = member.getItems().stream().map(
                item -> MyPageResponse.ItemResponse.builder()
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
                .acceptedDealRequests(acceptedDealRequests)
                .build();
    }

    public void handleDealRequest(HandleDealRequest req) {
        DealRequest dealRequest = dealRequestRepository.findById(req.getDealRequestId())
                .orElseThrow(DealRequestNotFound::new);
        Item item = dealRequest.getItem();
        if(req.isAccept()) {
            item.changeItemStatus(ItemStatus.RESERVE);
            dealRequest.toAccepted();
        }
        dealRequestRepository.deleteById(req.getDealRequestId());
    }
}
