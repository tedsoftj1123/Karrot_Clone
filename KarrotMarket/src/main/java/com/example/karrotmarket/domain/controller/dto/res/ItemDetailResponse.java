package com.example.karrotmarket.domain.controller.dto.res;

import com.example.karrotmarket.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailResponse {
    private String memberName;
    private String memberLocation;

    private String itemName;
    private String itemDescription;
    private String itemImgUrl;
    private int likeCount;
    private Category itemCategory;
    private boolean liked;
    private boolean negotiable;
    private int hits;
    private int price;

}
