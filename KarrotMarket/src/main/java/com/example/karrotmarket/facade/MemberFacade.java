package com.example.karrotmarket.facade;

import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.global.exception.TokenInvalidException;
import com.example.karrotmarket.global.exception.UserNotFoundException;
import com.example.karrotmarket.global.security.auth.AuthDetails;
import com.example.karrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;

    public Member getCurrentUser() {
        String detail =
                SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(detail);
        return memberRepository.findByMemberEmail(detail)
                .orElseThrow(UserNotFoundException::new);
    }
}
