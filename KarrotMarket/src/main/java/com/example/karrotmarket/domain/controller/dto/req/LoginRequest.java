package com.example.karrotmarket.domain.controller.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "email은 공백일 수 없습니다.")
    @Email(message = "email형식이 잘못되었습니다.")
    @Min(5)
    private String email;

    @NotBlank(message = "password는 공백일 수 없습니다.")
    private String password;
}
