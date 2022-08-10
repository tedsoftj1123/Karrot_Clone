package com.example.karrotmarket.domain.service;


import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.entity.DealRequest;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.ItemStatus;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.domain.repository.ItemRepository;
import com.example.karrotmarket.global.exception.DealRequestNotFound;
import com.example.karrotmarket.domain.repository.DealRequestRepository;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberFacade memberFacade;
    private final DealRequestRepository dealRequestRepository;
    private final ItemRepository itemRepository;
    @Transactional(readOnly = true)
    public MyPageResponse my() {
        Member currentUser = memberFacade.getCurrentUser();
        List<MyPageResponse.DealRequestResponse> outComingDealRequests = dealRequestRepository.findAllByDealMemberId(currentUser.getMemberId())
                .stream().map(
                        this::toDealRequestResponse
                ).collect(Collectors.toList());
        List<MyPageResponse.ItemResponse> memberItems = currentUser.getItems().stream().map(
                item -> MyPageResponse.ItemResponse.builder()
                        .itemName(item.getItemName())
                        .itemDescription(item.getItemDescription())
                        .createdAt(item.getCreatedAt())
                        .canNego(item.isNegotiable())
                        .price(item.getPrice())
                        .itemStatus(item.getItemStatus())
                        .build()
        ).collect(Collectors.toList());
        return MyPageResponse.builder()
                .memberId(currentUser.getMemberId())
                .memberName(currentUser.getMemberName())
                .memberEmail(currentUser.getMemberEmail())
                .memberAddress(currentUser.getAddress())
                .memberItems(memberItems)
                .outComingDealRequests(outComingDealRequests)
                .build();
    }

    public List<MyPageResponse.DealRequestResponse> inComingDealRequests() {
        Member currentUser = memberFacade.getCurrentUser();
        return currentUser.getDealRequests().stream()
                .map(this::toDealRequestResponse)
                .collect(Collectors.toList());
    }

    public MessageResponse acceptDealRequest(Long dealRequestId) {
        DealRequest dealRequest = dealRequestRepository.findById(dealRequestId)
                .orElseThrow(DealRequestNotFound::new);
        Item item = itemRepository.findById(dealRequest.getItem().getId()).orElseThrow(ItemNotExistsException::new);
        item.changeItemStatus(ItemStatus.RESERVE);
        dealRequestRepository.delete(dealRequest);
        return new MessageResponse("거래 요청이 수락되었습니다.");
    }
    public MessageResponse denyDealRequest(Long dealRequestId) {
        dealRequestRepository.deleteById(dealRequestId);
        return new MessageResponse("거래 요청이 거절되었습니다.");
    }
    private MyPageResponse.DealRequestResponse toDealRequestResponse(DealRequest d) {
        return MyPageResponse.DealRequestResponse.builder()
                .itemId(d.getItem().getId())
                .dealMember(d.getDealMemberId())
                .price(d.getPrice())
                .locationDetail(d.getLocationDetail())
                .timeDetail(d.getTimeDetail())
                .build();
    }
}
