package com.example.karrotmarket.domain.controller.dto.res;

import com.example.karrotmarket.domain.entity.Address;
import com.example.karrotmarket.domain.entity.DealRequestLocationDetail;
import com.example.karrotmarket.domain.entity.DealRequestTimeDetail;
import com.example.karrotmarket.domain.entity.ItemStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
public class MyPageResponse {
    private final String memberId;

    private final String memberName;

    private final Address memberAddress;

    private final List<DealRequestResponse> outGoingDealRequests;
    private final List<ItemResponse> memberItems;

    @Builder @Getter
    public static class DealRequestResponse {
        private final Long itemId;
        private final ItemStatus itemStatus;
        private final int price;
        private final DealRequestTimeDetail timeDetail;
        private final DealRequestLocationDetail locationDetail;
        private final String itemBuyerId;
    }

    @Builder @Getter
    public static class ItemResponse {
        private Long itemId;
        private String itemName;
        private String itemDescription;
        private LocalDateTime createdAt;
        private int price;
        private boolean canNego;
        private ItemStatus itemStatus;
    }


}
