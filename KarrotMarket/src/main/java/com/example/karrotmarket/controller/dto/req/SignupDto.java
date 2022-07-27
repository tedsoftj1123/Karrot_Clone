package com.example.karrotmarket.controller.dto.req;

import com.example.karrotmarket.domain.Address;
import com.example.karrotmarket.domain.Member;
import com.example.karrotmarket.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
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
