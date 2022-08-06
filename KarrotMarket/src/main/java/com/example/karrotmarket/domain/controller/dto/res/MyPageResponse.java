package com.example.karrotmarket.domain.controller.dto.res;

import com.example.karrotmarket.domain.entity.Address;
import com.example.karrotmarket.domain.entity.ItemStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
public class MyPageResponse {
    private final String memberId;

    private final String memberEmail;

    private final String memberName;

    private final Address memberAddress;

    private final List<List<DealRequestResponse>> inComingAndOutComingDealRequests;
    private final List<ItemResponse> memberItems;

    @Builder @Getter
    public static class DealRequestResponse {
        private final Long itemId;
        private final int price;
        private final String location;
        private final DayOfWeek day;
        private final String dealMember;
    }

    @Builder @Getter
    public static class ItemResponse {
        private String itemName;
        private String itemDescription;
        private LocalDateTime createdAt;
        private int price;
        private boolean canNego;
        private ItemStatus itemStatus;
    }


}
