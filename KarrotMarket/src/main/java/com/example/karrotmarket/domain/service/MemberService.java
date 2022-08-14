package com.example.karrotmarket.domain.service;


import com.example.karrotmarket.domain.controller.dto.req.ModifyItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.InComingDealRequestResponse;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.controller.dto.res.MyPageResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.entity.*;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.domain.repository.ItemRepository;
import com.example.karrotmarket.global.exception.CannotTurnUpException;
import com.example.karrotmarket.global.exception.DealRequestNotFound;
import com.example.karrotmarket.domain.repository.DealRequestRepository;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.global.exception.NoAuthorityException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        Member currentMember = memberFacade.getCurrentMember();
        List<MyPageResponse.DealRequestResponse> outGoingDealRequests = memberFacade.findMemberDealRequests(currentMember);
        List<MyPageResponse.ItemResponse> memberItems = memberFacade.findMemberItems(currentMember);
        return MyPageResponse.builder()
                .memberId(currentMember.getMemberId())
                .memberName(currentMember.getMemberName())
                .memberAddress(currentMember.getAddress())
                .memberItems(memberItems)
                .outGoingDealRequests(outGoingDealRequests)
                .build();
    }
    @Transactional(readOnly = true)
    public List<InComingDealRequestResponse> inComingDealRequests() {
        Member currentMember = memberFacade.getCurrentMember();
        return dealRequestRepository.findAllByItemOwnerOrderBySendTimeDesc(currentMember.getMemberId()).stream()
                .map(this::toDealRequestResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    @CacheEvict(value = "items", allEntries = true)
    public MessageResponse acceptDealRequest(Long dealRequestId) {
        Member currentMember = memberFacade.getCurrentMember();
        DealRequest dealRequest = dealRequestRepository.findById(dealRequestId)
                .orElseThrow(DealRequestNotFound::new);
        if(!dealRequest.getItemOwner().equals(currentMember.getMemberId())) {
            throw new NoAuthorityException();
        }
        Item item = itemRepository.findById(dealRequest.getItem().getId()).orElseThrow(ItemNotExistsException::new);
        dealRequest.toAccepted();
        item.changeItemStatus(ItemStatus.RESERVE);
        return new MessageResponse("거래 요청이 수락되었습니다.");
    }
    @Transactional
    public MessageResponse denyDealRequest(Long dealRequestId) {
        Member currentMember = memberFacade.getCurrentMember();
        DealRequest dealRequest = dealRequestRepository.findById(dealRequestId)
                        .orElseThrow(DealRequestNotFound::new);
        if(!dealRequest.getItemOwner().equals(currentMember.getMemberId())) {
            throw new NoAuthorityException();
        }
        dealRequestRepository.deleteById(dealRequestId);
        return new MessageResponse("거래 요청이 거절되었습니다.");
    }
    @Transactional
    @CacheEvict(value = "items", allEntries = true, cacheManager = "karrotCacheManager")
    public MessageResponse turnUpItem(Long itemId) {
        Member currentMember = memberFacade.getCurrentMember();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        memberFacade.validateUser(item, currentMember);
        if (LocalDateTime.now().minusDays(1L).isBefore(item.getCreatedAt())) {
            throw new CannotTurnUpException();
        }
        item.changeItemCreatedAt();
        return new MessageResponse("상품 끌올 성공");
    }
    @Transactional(readOnly = true)
    public List<ShowAllItemsResponse> bookMark() {
        Member currentMember = memberFacade.getCurrentMember();
        return currentMember.getHearts().stream()
                .map(Heart::getItem)
                .map(item -> ShowAllItemsResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .createdAt(item.getCreatedAt())
                        .location(item.getMember().getAddress().getDong())
                        .price(item.getPrice())
                        .likeCount(item.getLikeCount().size())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(value = "items", allEntries = true, cacheManager = "karrotCacheManager")
    public MessageResponse modifyItem(Long itemId, ModifyItemRequest req) {
        Member currentMember = memberFacade.getCurrentMember();
        Item item = itemRepository.findById(itemId)
                        .orElseThrow(ItemNotExistsException :: new);
        memberFacade.validateUser(item, currentMember);

        item.modifyItemInfo(req);
        return new MessageResponse("상품 정보 변경 완료");
    }

    private InComingDealRequestResponse toDealRequestResponse(DealRequest d) {
        return InComingDealRequestResponse.builder()
                .itemId(d.getItem().getId())
                .dealRequestId(d.getId())
                .itemBuyerId(d.getItemBuyer().getMemberId())
                .price(d.getPrice())
                .sendTime(d.getSendTime())
                .itemStatus(d.getItem().getItemStatus())
                .locationDetail(d.getLocationDetail())
                .timeDetail(d.getTimeDetail())
                .build();
    }
    @Transactional(readOnly = true)
    public List<ShowAllItemsResponse> completedItems() {
        Member member = memberFacade.getCurrentMember();
        return member.getItems().stream()
                .filter(i -> i.getItemStatus().equals(ItemStatus.COMP))
                .map(item -> ShowAllItemsResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .createdAt(item.getCreatedAt())
                        .location(item.getMember().getAddress().getDong())
                        .price(item.getPrice())
                        .likeCount(item.getLikeCount().size())
                        .build()
                ).collect(Collectors.toList());
    }
    @Transactional
    public MessageResponse completeDeal(Long itemId) {
        Member member = memberFacade.getCurrentMember();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        memberFacade.validateUser(item, member);
        item.changeItemStatus(ItemStatus.COMP);
        dealRequestRepository.deleteAllByItem(item);
        return new MessageResponse(item.getItemName() + "삼품의 거래가 완료되었습니다.");
    }

}
