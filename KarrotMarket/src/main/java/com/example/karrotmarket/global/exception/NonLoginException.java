package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class NonLoginException extends BaseException {
    public NonLoginException() {
        super(ErrorCode.NON_LOGIN);
    }
}
