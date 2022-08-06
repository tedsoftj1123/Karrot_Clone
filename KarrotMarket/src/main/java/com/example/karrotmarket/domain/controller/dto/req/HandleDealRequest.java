package com.example.karrotmarket.domain.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HandleDealRequest {
    private boolean accept;
    private Long dealRequestId;
}
