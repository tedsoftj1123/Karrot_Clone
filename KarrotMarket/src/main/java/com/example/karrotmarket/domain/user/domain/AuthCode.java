package com.example.karrotmarket.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(timeToLive = 60)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthCode {
    @Id
    private String email;

    private String code;

    @Builder
    public AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
