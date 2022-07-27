package com.example.karrotmarket.controller;

import com.example.karrotmarket.controller.dto.TokenDto;
import com.example.karrotmarket.controller.dto.req.LoginDto;
import com.example.karrotmarket.controller.dto.req.SignupDto;
import com.example.karrotmarket.controller.dto.req.TokenRequestDto;
import com.example.karrotmarket.controller.dto.res.MemberResponseDto;
import com.example.karrotmarket.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto signup(@RequestBody SignupDto req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/reissue")
    public TokenDto reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return authService.reissue(tokenRequestDto);
    }
}
