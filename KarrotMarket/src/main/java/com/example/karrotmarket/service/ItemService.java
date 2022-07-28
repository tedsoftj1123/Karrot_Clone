package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.req.ItemRequest;
import com.example.karrotmarket.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.ItemStatus;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;

    public AddItemResponse addItem(ItemRequest req) {
        Member member = memberFacade.getCurrentUser();
        itemRepository.save(
                Item.builder()
                        .itemName(req.getItemName())
                        .itemDescription(req.getItemDescription())
                        .canNegotiate(req.isCanNego())
                        .itemStatus(ItemStatus.SALE)
                        .createdAt(LocalDateTime.now())
                        .price(req.getItemPrice())
                        .member(member)
                        .build()
        );
        return AddItemResponse.builder()
                .itemName(req.getItemName())
                .memberName(member.getMemberName())
                .build();
    }
}
