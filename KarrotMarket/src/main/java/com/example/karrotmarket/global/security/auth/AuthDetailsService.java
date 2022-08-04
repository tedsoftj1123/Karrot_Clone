package com.example.karrotmarket.global.security.auth;

import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(uid)
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("cannot find user"));
    }
}
