package com.example.karrotmarket.controller.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private ZonedDateTime accessTokenExpiresIn;
}
