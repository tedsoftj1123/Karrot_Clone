package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.req.ItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.domain.controller.dto.res.ItemDetailResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.ItemStatus;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.domain.repository.HeartRepository;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;
    private final HeartRepository heartRepository;

    @Transactional
    @CacheEvict(value = "items", allEntries = true)
    public AddItemResponse addItem(ItemRequest req) {
        Member member = memberFacade.getCurrentUser();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/item/download-img")
                .queryParam("fileName", req.getItemName())
                .toUriString();
        itemRepository.save(
                Item.builder()
                        .itemName(req.getItemName())
                        .category(req.getItemCategory())
                        .itemImgUrl(fileDownloadUri)
                        .itemDescription(req.getItemDescription())
                        .negotiable(req.isNegotiable())
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

    @Cacheable(value = "items")
    public List<ShowAllItemsResponse> main() {
        Member member = memberFacade.getCurrentUser();
        return itemRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(item -> item.getMember().getAddress().getDong().equals(member.getAddress().getDong()))
                .filter(i -> i.getItemStatus().equals(ItemStatus.SALE))
                .map(item -> ShowAllItemsResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .createdAt(item.getCreatedAt())
                        .liked(heartRepository.existsByMemberAndItem(member, item))
                        .location(item.getMember().getAddress().getDong())
                        .price(item.getPrice())
                        .likeCount(item.getLikeCount().size())
                        .build()
        ).collect(Collectors.toList());
    }
    @Transactional
    @Cacheable(value = "userItem", key = "#itemId")
    public ItemDetailResponse itemDetail(Long itemId) {
        final Member member = memberFacade.getCurrentUser();
        final Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        item.addViewCount();
        return ItemDetailResponse.builder()
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .itemCategory(item.getCategory())
                .itemImgUrl(item.getItemImgUrl())
                .negotiable(item.isNegotiable())
                .liked(heartRepository.existsByMemberAndItem(member, item))
                .memberName(item.getMember().getMemberName())
                .memberLocation(item.getMember().getAddress().getCity() + " " + item.getMember().getAddress().getDong())
                .price(item.getPrice())
                .hits(item.getViews())
                .likeCount(item.getLikeCount().size())
                .build();
    }
}
