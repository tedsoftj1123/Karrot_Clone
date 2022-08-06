package com.example.karrotmarket.domain.controller.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Getter
@NoArgsConstructor
public class UserDealRequest {
    private DayOfWeek day;
    private String location;
    private Integer price;

    public boolean negoCheck(boolean canNego) {
        return !canNego && !price.equals(0);
    }
}
