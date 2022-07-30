package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.req.UserDealRequest;
import com.example.karrotmarket.domain.DealRequest;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.repository.DealRequestRepository;
import com.example.karrotmarket.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DealRequestService {
    private final DealRequestRepository dealRequestRepository;
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;
    public void sendDealRequest(Long todoId, UserDealRequest req) {
        Member member = memberFacade.getCurrentUser();
        Item item = itemRepository.findById(todoId)
                .orElseThrow(ItemNotExistsException::new);
        dealRequestRepository.save(
                DealRequest.builder()
                        .item(item)
                        .member(item.getMember())
                        .dealMember(member.getMemberId())
                        .day(req.getDay())
                        .location(req.getLocation())
                        .price(item.isCanNegotiate() ? req.getPrice() : item.getPrice())
                        .build()
        );
    }
}
