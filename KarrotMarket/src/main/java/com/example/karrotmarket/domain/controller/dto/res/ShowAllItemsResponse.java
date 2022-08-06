package com.example.karrotmarket.domain.controller.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder @Getter
@AllArgsConstructor
public class ShowAllItemsResponse {
    private Long itemId;
    private String itemName;
    private String location;
    private int price;
    private int likeCount;
    private int itemDealRequestCount;


}
