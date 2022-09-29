package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class ExpiredJwtException extends KarrotException {
    public ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
