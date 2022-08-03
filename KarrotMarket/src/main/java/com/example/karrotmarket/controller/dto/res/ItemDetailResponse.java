package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class ItemDetailResponse {
    private String memberName;
    private String memberLocation;

    private String itemName;
    private String itemDescription;
    private int likeCount;
    private Category itemCategory;
    private int hits;
    private int price;

}