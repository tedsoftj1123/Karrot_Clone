package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.user.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private final String email;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getMemberEmail());
    }
}