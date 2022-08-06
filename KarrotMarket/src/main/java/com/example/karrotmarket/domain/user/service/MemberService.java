package com.example.karrotmarket.domain.user.service;

import com.example.karrotmarket.domain.user.controller.dto.request.HandleDealRequest;
import com.example.karrotmarket.domain.user.controller.dto.response.DealRequestResponse;
import com.example.karrotmarket.domain.user.controller.dto.response.ItemResponse;
import com.example.karrotmarket.domain.user.controller.dto.response.MyPageResponse;
import com.example.karrotmarket.domain.DealRequest;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.ItemStatus;
import com.example.karrotmarket.domain.user.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.domain.user.exception.DealRequestNotFound;
import com.example.karrotmarket.repository.DealRequestRepository;
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
        List<DealRequestResponse> acceptedDealRequests = member.getDealRequests()
                .stream().filter(DealRequest::isAccepted)
                .map(d -> DealRequestResponse.builder()
                        .dealMember(d.getDealMember())
                        .price(d.getPrice())
                        .day(d.getDay())
                        .location(d.getLocation())
                        .build())
                .collect(Collectors.toList());
        return MyPageResponse.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberAddress(member.getAddress())
                .acceptedDealRequests(acceptedDealRequests)
                .build();
    }

    public List<ItemResponse> userItems() {
        Member member = memberFacade.getCurrentUser();
        return member.getItems().stream().map(
                item -> ItemResponse.builder()
                        .itemName(item.getItemName())
                        .itemDescription(item.getItemDescription())
                        .createdAt(item.getCreatedAt())
                        .canNego(item.isCanNegotiate())
                        .price(item.getPrice())
                        .itemStatus(item.getItemStatus())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<DealRequestResponse> userDealRequests() {
        Member member = memberFacade.getCurrentUser();
        return member.getDealRequests().stream()
                .map(dealRequest -> DealRequestResponse.builder()
                        .dealMember(dealRequest.getDealMember())
                        .day(dealRequest.getDay())
                        .location(dealRequest.getLocation())
                        .price(dealRequest.getPrice())
                        .build()
                ).collect(Collectors.toList());
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
