package com.example.karrotmarket.global.security.jwt;


import com.example.karrotmarket.domain.entity.RefreshToken;
import com.example.karrotmarket.global.exception.InvalidJwtException;
import com.example.karrotmarket.global.security.auth.AuthDetailsService;
import com.example.karrotmarket.domain.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String uid) {
        return generateToken(uid, "access", jwtProperties.getAccessExp());
    }
    public String generateRefreshToken(String uid) {
        String refresh = generateToken(uid, "refresh", jwtProperties.getRefreshExp());
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .key(uid)
                        .value(refresh)
                        .build()
        );
        return refresh;
    }

    private String generateToken(String uid, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(uid)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new com.example.karrotmarket.global.exception.ExpiredJwtException();
        } catch (Exception e) {
            throw new InvalidJwtException();
        }
    }
}
