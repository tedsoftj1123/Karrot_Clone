package com.example.karrotmarket.controller.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private boolean canNego;
}
