package com.example.karrotmarket.domain.facade;

import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.global.exception.UserNotFoundException;
import com.example.karrotmarket.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return memberRepository.findByMemberId(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
