package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class RefreshTokenExpiredException extends BaseException {
    public RefreshTokenExpiredException() {
        super(ErrorCode.EXPIRED_REFRESH);
    }
}
