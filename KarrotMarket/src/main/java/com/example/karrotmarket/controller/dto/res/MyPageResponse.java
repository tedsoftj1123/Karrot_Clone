package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.Address;
import com.example.karrotmarket.domain.DealRequest;
import com.example.karrotmarket.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MyPageResponse {
    private String memberId;

    private String memberEmail;

    private String memberName;

    private Address memberAddress;

    private List<Item> memberItems;

    private List<DealRequest> memberDealRequests;
}
