package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.DealRequestLocationDetail;
import com.example.karrotmarket.domain.entity.DealRequestTimeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Getter
@NoArgsConstructor
public class UserDealRequest {
    private DealRequestTimeDetail timeDetail;
    private DealRequestLocationDetail locationDetail;
    @NotNull(message = "희망 거래 금액을 입력해주세요")
    private int price;
}
