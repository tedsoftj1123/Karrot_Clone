package com.example.karrotmarket.service;

import com.example.karrotmarket.domain.Heart;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.facade.MemberFacade;
import com.example.karrotmarket.global.exception.ItemNotExistsException;
import com.example.karrotmarket.repository.HeartRepository;
import com.example.karrotmarket.repository.ItemRepository;
import com.example.karrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
