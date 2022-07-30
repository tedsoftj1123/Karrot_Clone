package com.example.karrotmarket.controller.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Getter
@NoArgsConstructor
public class UserDealRequest {
    private DayOfWeek day;
    private String location;
    private int price;
}
