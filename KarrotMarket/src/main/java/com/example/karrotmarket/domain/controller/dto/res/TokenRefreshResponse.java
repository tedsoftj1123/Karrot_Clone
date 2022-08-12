package com.example.karrotmarket.domain.controller.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenRefreshResponse {
    private final String accessToken;
}
