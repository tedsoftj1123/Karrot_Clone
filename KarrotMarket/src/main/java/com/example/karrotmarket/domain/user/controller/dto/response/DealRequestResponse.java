package com.example.karrotmarket.domain.user.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;


@Builder @Getter
public class DealRequestResponse {
    private final int price;
    private final String location;
    private final DayOfWeek day;
    private final String dealMember;
}
