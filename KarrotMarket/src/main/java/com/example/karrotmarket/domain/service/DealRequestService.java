package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.req.UserDealRequest;
import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.entity.DealRequest;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.global.exception.CannotNegoException;
import com.example.karrotmarket.global.exception.DealRequestAlreadyExistsException;
import com.example.karrotmarket.global.exception.DealRequestNotFound;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.domain.repository.DealRequestRepository;
import com.example.karrotmarket.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DealRequestService {
    private final DealRequestRepository dealRequestRepository;
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;

    @Transactional
    public MessageResponse sendDealRequest(Long todoId, UserDealRequest req) {
        Member currentMember = memberFacade.getCurrentMember();
        Item item = itemRepository.findById(todoId)
                .orElseThrow(ItemNotExistsException::new);
        if (dealRequestRepository.existsByItemBuyerAndItem(currentMember, item)) {
            throw new DealRequestAlreadyExistsException();
        }
        if(!item.isNegotiable() && req.getPrice()!=item.getPrice()){
            throw new CannotNegoException();
        }
        System.out.println(req.getLocationDetail().getDong());
        dealRequestRepository.save(
                DealRequest.builder()
                        .item(item)
                        .itemOwner(item.getMember().getMemberId())
                        .itemBuyer(currentMember)
                        .timeDetail(req.getTimeDetail())
                        .locationDetail(req.getLocationDetail())
                        .price(req.getPrice())
                        .sendTime(LocalDateTime.now())
                        .build()
        );
        String resMsg = item.getMember().getMemberId() + "님의 상품 "
                + item.getItemName() + "에 거래 요청을 전송했습니다.";
        return new MessageResponse(resMsg);
    }

}
