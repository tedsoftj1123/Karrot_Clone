package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.TokenDto;
import com.example.karrotmarket.controller.dto.req.LoginRequest;
import com.example.karrotmarket.controller.dto.req.SignupRequest;
import com.example.karrotmarket.controller.dto.req.TokenRequestDto;
import com.example.karrotmarket.controller.dto.res.MemberResponseDto;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.domain.RefreshToken;
import com.example.karrotmarket.global.exception.UserAlreadyExistsException;
import com.example.karrotmarket.global.security.jwt.JwtTokenProvider;
import com.example.karrotmarket.repository.MemberRepository;
import com.example.karrotmarket.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public MemberResponseDto signup(SignupRequest req) {
        validateDuplicateMember(req.getMemberEmail());

        Member member = req.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(LoginRequest req) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }

    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        if(!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token not valid");
        }
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User logout"));
        if(!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Wrong user info");
        }
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        RefreshToken newRefresh = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefresh);

        return tokenDto;
    }
    private void validateDuplicateMember(String memberEmail) {
        if(memberRepository.existsByMemberEmail(memberEmail)){
            throw new UserAlreadyExistsException();
        }
    }
}
