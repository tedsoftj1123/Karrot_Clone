package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class DealRequestNotFound extends KarrotException {
    public DealRequestNotFound() {
        super(ErrorCode.DEAL_REQUEST_NOT_FOUND);
    }
}
