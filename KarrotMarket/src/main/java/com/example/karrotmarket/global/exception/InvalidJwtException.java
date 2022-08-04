package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

import java.util.prefs.BackingStoreException;

public class InvalidJwtException extends BaseException {
    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
