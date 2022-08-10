package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class ItemRequest {
    @NotEmpty
    private String itemName;
    @NotEmpty
    @Size(min = 10, max = 100)
    private String itemDescription;
    @Positive
    @NotBlank
    private int itemPrice;
    @NotNull
    private boolean negotiable;
    @NotBlank
    private Category itemCategory;
}
