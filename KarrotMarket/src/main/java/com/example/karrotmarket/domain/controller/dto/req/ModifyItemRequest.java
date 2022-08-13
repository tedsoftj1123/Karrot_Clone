package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ModifyItemRequest {
    @NotEmpty(message = "변경할 물품이름을 입력해주세요")
    @Size(max = 10, message = "변경할 물품이름은 10글자보다 길어선 안됩니다.")
    private String itemName;
    @NotEmpty
    @Size(min = 10, max = 100, message = "번경할 물품 설명은 10글자보다 길고, 100글자보다 짧아야 합니다.")
    private String itemDescription;
    @Positive(message = "번경할 물품가격은 0원보타 커야합니다.")
    @NotNull(message = "변경할 물품가격을 입력해주세요")
    private int itemPrice;
    @NotNull(message = "변경할 네고가능여부를 입력해주세요")
    private boolean negotiable;
    @NotNull(message = "변경할 물품 카테고리를 입력해주세요")
    private Category itemCategory;
}
