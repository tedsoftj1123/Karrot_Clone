package com.example.karrotmarket.domain.facade;

import com.example.karrotmarket.domain.entity.Member;
import com.example.karrotmarket.global.exception.TokenInvalidException;
import com.example.karrotmarket.global.exception.UserNotFoundException;
import com.example.karrotmarket.domain.repository.MemberRepository;
import com.example.karrotmarket.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;

    public Member getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            throw new TokenInvalidException();
        }

        return memberRepository.findByMemberEmail(((AuthDetails)principal).getUsername())
                .orElseThrow(UserNotFoundException::new);
    }
}
