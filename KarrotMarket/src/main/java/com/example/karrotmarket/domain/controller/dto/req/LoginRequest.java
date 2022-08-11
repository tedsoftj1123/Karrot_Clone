package com.example.karrotmarket.domain.controller.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "memberId는 공백일 수 없습니다.")
    private String memberId;

    @NotBlank(message = "password는 공백일 수 없습니다.")
    private String password;
}
