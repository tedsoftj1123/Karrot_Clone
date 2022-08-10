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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {
    private final HeartRepository heartRepository;
    private final ItemRepository itemRepository;
    private final MemberFacade memberFacade;

    public MessageResponse doLike(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        String memberId = memberFacade.getCurrentUser().getMemberId();
        if(heartRepository.existsByMemberIdAndItem(memberId, item)){
            return unlike(memberId, item);
        } else {
            return like(memberId, item);
        }
    }

    private MessageResponse like(String memberId, Item item) {
        heartRepository.save(
                Heart.builder()
                        .memberId(memberId)
                        .item(item)
                        .build()
        );
        return new MessageResponse("상품에 좋아요를 눌렀습니다.");
    }
    private MessageResponse unlike(String memberId, Item item) {
        heartRepository.deleteByMemberIdAndItem(memberId, item);
        return new MessageResponse("상품의 좋아요를 취소했습니다.");
    }
}
