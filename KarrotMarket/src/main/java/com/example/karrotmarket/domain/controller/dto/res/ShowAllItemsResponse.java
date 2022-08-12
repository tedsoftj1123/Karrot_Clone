package com.example.karrotmarket.domain.controller.dto.res;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllItemsResponse {
    private Long itemId;
    private String itemName;
    private String location;
    private boolean liked;
    private LocalDateTime createdAt;
    private int price;
    private int likeCount;


}
