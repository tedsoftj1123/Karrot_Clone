package com.example.karrotmarket.controller.dto.res;

import com.fasterxml.jackson.datatype.jdk8.OptionalIntDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Builder @Getter
public class ShowAllItemsResponse {
    private final Long itemId;
    private final String itemName;
    private final String location;
    private final LocalDateTime displayTime;
    private final int price;
    private final int likeCount;
}
