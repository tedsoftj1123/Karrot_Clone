package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.DealRequestLocationDetail;
import com.example.karrotmarket.domain.entity.DealRequestTimeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Getter
@NoArgsConstructor
public class UserDealRequest {
    private DealRequestTimeDetail timeDetail;
    private DealRequestLocationDetail locationDetail;
    private int price;
}
