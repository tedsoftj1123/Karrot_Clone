package com.example.karrotmarket.global.security.jwt;


import com.example.karrotmarket.domain.entity.RefreshToken;
import com.example.karrotmarket.global.exception.InvalidJwtException;
import com.example.karrotmarket.global.security.auth.AuthDetailsService;
import com.example.karrotmarket.domain.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private Key key;
    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
    }

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
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(uid)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateAccessToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new com.example.karrotmarket.global.exception.ExpiredJwtException();
        } catch (Exception e) {
            throw new InvalidJwtException();
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtProperties.getSecretKey()).build()
                    .parseClaimsJws(refreshToken);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new com.example.karrotmarket.global.exception.RefreshTokenExpiredException();
        } catch (Exception e) {
            throw new InvalidJwtException();
        }
    }
}
