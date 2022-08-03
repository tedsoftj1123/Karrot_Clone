package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder @Getter
public class ItemDetailResponse {
    private final String memberName;
    private final String memberLocation;

    private final String itemName;
    private final String itemDescription;
    private final int likeCount;
    private final Category itemCategory;
    private final int hits;
    private final int price;

}
