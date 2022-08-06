package com.example.karrotmarket.domain.user.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class DealRequestNotFound extends BaseException {
    public DealRequestNotFound() {
        super(ErrorCode.DEAL_REQUEST_NOT_FOUND);
    }
}
