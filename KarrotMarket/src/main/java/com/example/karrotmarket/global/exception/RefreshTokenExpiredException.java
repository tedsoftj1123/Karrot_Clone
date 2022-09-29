package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class RefreshTokenExpiredException extends KarrotException {
    public RefreshTokenExpiredException() {
        super(ErrorCode.EXPIRED_REFRESH);
    }
}
