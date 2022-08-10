package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ModifyItemRequest {
    @NotEmpty
    private String itemName;

    @NotEmpty
    private String itemDescription;

    @NotEmpty
    private Category itemCategory;

    @NotNull
    private boolean itemNegotiable;

    @NotNull
    private int itemPrice;
}
