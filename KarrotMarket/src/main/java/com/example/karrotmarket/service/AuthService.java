package com.example.karrotmarket.service;

import com.example.karrotmarket.controller.dto.res.TokenRefreshResponse;
import com.example.karrotmarket.controller.dto.res.TokenResponse;
import com.example.karrotmarket.controller.dto.req.LoginRequest;
import com.example.karrotmarket.controller.dto.req.SignupRequest;
import com.example.karrotmarket.controller.dto.res.MemberResponseDto;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.domain.RefreshToken;
import com.example.karrotmarket.global.exception.RefreshTokenNotFoundException;
import com.example.karrotmarket.global.exception.UserAlreadyExistsException;
import com.example.karrotmarket.global.exception.UserNotFoundException;
import com.example.karrotmarket.global.exception.WrongPasswordException;
import com.example.karrotmarket.global.security.jwt.JwtTokenProvider;
import com.example.karrotmarket.repository.MemberRepository;
import com.example.karrotmarket.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    @Transactional
    public MemberResponseDto signup(SignupRequest req) {
        validateDuplicateMember(req.getMemberEmail());

        Member member = req.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }
    public TokenResponse login(LoginRequest req) {
        Member member = memberRepository.findByMemberEmail(req.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(req.getPassword(), member.getMemberPassword())) {
            throw new WrongPasswordException();
        }
        String accessToken = jwtTokenProvider.generateAccessToken(member.getMemberId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getMemberId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtTokenProvider.getExpiredTime())
                .build();
    }
    public TokenRefreshResponse reissue(String refreshToken) {
        RefreshToken userRefreshToken = refreshTokenRepository.findByKey(jwtTokenProvider.getUserPk(refreshToken))
                .orElseThrow(RefreshTokenNotFoundException::new);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userRefreshToken.getKey());
        userRefreshToken.updateValue(newRefreshToken);

        String accessToken = jwtTokenProvider.generateAccessToken(userRefreshToken.getKey());
        return TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
    private void validateDuplicateMember(String memberEmail) {
        if(memberRepository.existsByMemberEmail(memberEmail)){
            throw new UserAlreadyExistsException();
        }
    }
}
