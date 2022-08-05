package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.Address;
import com.example.karrotmarket.domain.DealRequest;
import com.example.karrotmarket.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
public class MyPageResponse {
    private final String memberId;

    private final String memberEmail;

    private final String memberName;

    private final Address memberAddress;

    private final List<ItemResponse> memberItems;

    private final List<DealRequestResponse> memberDealRequests;
}
