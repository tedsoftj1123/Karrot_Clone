package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.req.UserDealRequest;
import com.example.karrotmarket.domain.entity.DealRequest;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.global.exception.CannotNegoException;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.domain.repository.DealRequestRepository;
import com.example.karrotmarket.domain.repository.ItemRepository;
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
        if(req.negoCheck(item.isCanNegotiate())){
            throw new CannotNegoException();
        }
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
