package com.example.karrotmarket.domain.service;

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

    public void doLike(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotExistsException::new);
        Member member = memberFacade.getCurrentUser();
        if(heartRepository.existsByMemberAndItem(member, item)){
            unlike(member, item);
        } else {
            like(member, item);
        }
    }

    private void like(Member member, Item item) {
        heartRepository.save(
                Heart.builder()
                        .member(member)
                        .item(item)
                        .build()
        );
    }
    private void unlike(Member member, Item item) {
        heartRepository.deleteByMemberAndItem(member, item);
    }
}
