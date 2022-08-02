package com.example.karrotmarket.controller.dto.req;

import com.example.karrotmarket.domain.Address;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank(message = "id는 공백이나 뛰어쓰기를 허용하지 않습니다.")
    private String memberId;

    @NotBlank(message = "name은 공백이나 뛰어쓰기를 허용하지 않습니다.")
    private String memberName;

    @NotBlank(message = "email는 공백이나 뛰어쓰기를 허용하지 않습니다.")
    @Email(message = "잘못된 email형식입니다.")
    private String memberEmail;

    @NotBlank(message = "password는 공백이나 뛰어쓰기를 허용하지 않습니다.")
    private String memberPassword;

    @NotBlank(message = "address는 공백을 허용하지 않습니다.")
    private Address address;
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .address(address)
                .memberEmail(memberEmail)
                .memberPassword(passwordEncoder.encode(memberPassword))
                .role(Role.ROLE_USER)
                .build();
    }
}
