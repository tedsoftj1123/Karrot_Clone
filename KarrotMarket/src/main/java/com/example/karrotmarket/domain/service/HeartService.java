package com.example.karrotmarket.domain.service;

import com.example.karrotmarket.domain.controller.dto.res.MessageResponse;
import com.example.karrotmarket.domain.entity.Heart;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.domain.facade.MemberFacade;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.domain.repository.HeartRepository;
import com.example.karrotmarket.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {
    private final HeartRepository heartRepository;
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;

    @CacheEvict(value = "items", allEntries = true, cacheManager = "karrotCacheManager")
    public MessageResponse doLike(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        Member member = memberFacade.getCurrentMember();
        if(heartRepository.existsByMemberAndItem(member, item)){
            return unlike(member, item);
        } else {
            return like(member, item);
        }
    }

    private MessageResponse like(Member member, Item item) {
        heartRepository.save(
                Heart.builder()
                        .member(member)
                        .item(item)
                        .build()
        );
        return new MessageResponse("상품에 좋아요를 눌렀습니다.");
    }
    private MessageResponse unlike(Member member, Item item) {
        heartRepository.deleteByMemberAndItem(member, item);
        return new MessageResponse("상품의 좋아요를 취소했습니다.");
    }
}
