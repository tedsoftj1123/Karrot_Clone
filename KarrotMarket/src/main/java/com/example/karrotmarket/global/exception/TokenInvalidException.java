package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class TokenInvalidException extends BaseException {
    public TokenInvalidException() {
        super(ErrorCode.TOKEN_INVALID);
    }
}
