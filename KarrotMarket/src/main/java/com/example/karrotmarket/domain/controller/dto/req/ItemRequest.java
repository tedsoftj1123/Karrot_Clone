package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private boolean negotiable;
    private Category itemCategory;
}
