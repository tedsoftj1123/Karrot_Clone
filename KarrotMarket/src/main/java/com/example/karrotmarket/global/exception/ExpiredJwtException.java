package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class ExpiredJwtException extends BaseException {
    public ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
