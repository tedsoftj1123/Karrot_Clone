package com.example.karrotmarket.domain.controller.dto.res;

import com.example.karrotmarket.domain.entity.DealRequestLocationDetail;
import com.example.karrotmarket.domain.entity.DealRequestTimeDetail;
import com.example.karrotmarket.domain.entity.ItemStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class InComingDealRequestResponse{
    private final Long itemId;
    private final Long dealRequestId;
    private final ItemStatus itemStatus;
    private final int price;
    private final DealRequestTimeDetail timeDetail;
    private final DealRequestLocationDetail locationDetail;
    private final LocalDateTime sendTime;
    private final String itemBuyerId;
}
