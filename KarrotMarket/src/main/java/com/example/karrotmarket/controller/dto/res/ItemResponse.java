package com.example.karrotmarket.controller.dto.res;

import com.example.karrotmarket.domain.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder @Getter
public class ItemResponse {
    private String itemName;
    private String itemDescription;
    private LocalDateTime createdAt;
    private int price;
    private boolean canNego;
    private ItemStatus itemStatus;
}
