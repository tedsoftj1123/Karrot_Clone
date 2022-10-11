package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class InvalidJwtException extends KarrotException {
    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
