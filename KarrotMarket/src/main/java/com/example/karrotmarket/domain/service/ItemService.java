package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.req.ItemRequest;
import com.example.karrotmarket.domain.controller.dto.res.AddItemResponse;
import com.example.karrotmarket.domain.controller.dto.res.ItemDetailResponse;
import com.example.karrotmarket.domain.controller.dto.res.ShowAllItemsResponse;
import com.example.karrotmarket.domain.entity.Hits;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.ItemStatus;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.domain.repository.HitsRepository;
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
    private final HitsRepository hitsRepository;

    @Transactional
    @CacheEvict(value = "items", allEntries = true, cacheManager = "testCacheManager")
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

    @Cacheable(value = "items", cacheManager = "testCacheManager")
    public List<ShowAllItemsResponse> main() {
        return itemRepository.findAll().stream()
                .filter(i -> i.getItemStatus().equals(ItemStatus.SALE))
                .map(item -> ShowAllItemsResponse.builder()
                        .itemId(item.getId())
                        .itemName(item.getItemName())
                        .location(item.getMember().getAddress().getDong())
                        .price(item.getPrice())
                        .likeCount(item.getLikeCount().size())
                        .build()
        ).collect(Collectors.toList());
    }
    @Transactional
    @Cacheable(value = "ItemDetailResponse", key = "#itemId", cacheManager = "testCacheManager")
    public ItemDetailResponse itemDetail(Long itemId) {
        final Member member = memberFacade.getCurrentUser();
        final Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        if(!hitsRepository.existsByMemberIdAndItem(member.getId(), item)) {
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
                .itemImgUrl(item.getItemImgUrl())
                .memberName(item.getMember().getMemberName())
                .memberLocation(item.getMember().getAddress().getCity() + " " + item.getMember().getAddress().getDong())
                .price(item.getPrice())
                .hits(item.getHits().size())
                .likeCount(item.getLikeCount().size())
                .build();
    }
}
