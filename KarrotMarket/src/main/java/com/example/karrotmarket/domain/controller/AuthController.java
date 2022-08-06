package com.example.karrotmarket.domain.controller;

import com.example.karrotmarket.domain.controller.dto.res.TokenRefreshResponse;
import com.example.karrotmarket.domain.controller.dto.res.TokenResponse;
import com.example.karrotmarket.domain.controller.dto.req.LoginRequest;
import com.example.karrotmarket.domain.controller.dto.req.SignupRequest;
import com.example.karrotmarket.domain.controller.dto.res.MemberResponseDto;
import com.example.karrotmarket.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto signup(@Valid @RequestBody SignupRequest req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginDto) {
        return authService.login(loginDto);
    }

    @PutMapping("/reissue")
    public TokenRefreshResponse reissue(@RequestHeader("Refresh-Token") String token) {
        return authService.reissue(token);
    }
}
