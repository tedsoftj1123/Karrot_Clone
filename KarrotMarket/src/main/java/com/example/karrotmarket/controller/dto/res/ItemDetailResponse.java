package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



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
    private int hits;
    private int price;

}
