package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.req.ItemRequest;
import com.example.karrotmarket.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.controller.dto.res.ItemDetailResponse;
import com.example.karrotmarket.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.Hits;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.ItemStatus;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.repository.HitsRepository;
import com.example.karrotmarket.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;
    private final HitsRepository hitsRepository;

    @Transactional
    public AddItemResponse addItem(ItemRequest req) {
        Member member = memberFacade.getCurrentUser();
        itemRepository.save(
                Item.builder()
                        .itemName(req.getItemName())
                        .category(req.getItemCategory())
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

    public List<ShowAllItemsResponse> main() {
        return itemRepository.findAll().stream()
                .filter(i -> i.getItemStatus().equals(ItemStatus.SALE))
                .map(item -> ShowAllItemsResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .location(item.getMember().getAddress().getDong())
                        .price(item.getPrice())
                        .displayTime(item.getCreatedAt())
                        .hits(item.getHits().size())
                        .likeCount(item.getLikeCount().size())
                        .build()
        ).collect(Collectors.toList());
    }
    @Transactional
    public ItemDetailResponse itemDetail(Long itemId) {
        Member member = memberFacade.getCurrentUser();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        if(!hitsRepository.existsByMemberIdAndItem(member.getId(), item)) {
            System.out.println("asdf");
            hitsRepository.save(
                    Hits.builder()
                            .memberId(member.getId())
                            .item(item)
                            .build()
            );
        }
        return ItemDetailResponse.builder()
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .itemCategory(item.getCategory())
                .memberName(item.getMember().getMemberName())
                .memberLocation(item.getMember().getAddress().getCity() + " " + item.getMember().getAddress().getDong())
                .price(item.getPrice())
                .hits(item.getHits().size())
                .likeCount(item.getLikeCount().size())
                .build();
    }
}
