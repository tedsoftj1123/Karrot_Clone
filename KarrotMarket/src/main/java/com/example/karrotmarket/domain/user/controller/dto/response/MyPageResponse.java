package com.example.karrotmarket.domain.user.controller.dto.response;

import com.example.karrotmarket.domain.user.domain.Address;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class MyPageResponse {
    private final String memberId;

    private final String memberEmail;

    private final String memberName;

    private final Address memberAddress;

    private final List<DealRequestResponse> acceptedDealRequests;
}
