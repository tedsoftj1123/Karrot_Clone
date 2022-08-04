package com.example.karrotmarket.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jdk8.OptionalIntDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class ShowAllItemsResponse {
    private Long itemId;
    private String itemName;
    private String location;
    private int price;
    private int likeCount;
    private int itemDealRequestCount;


}
