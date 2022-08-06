package com.example.karrotmarket.domain.controller.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class AddItemResponse {
    private final String itemName;
    private final String memberName;
}
