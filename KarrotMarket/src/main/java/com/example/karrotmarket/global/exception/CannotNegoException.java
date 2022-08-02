package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class CannotNegoException extends BaseException {
    public CannotNegoException() {
        super(ErrorCode.CANNOT_NEGOTIATE);
    }
}
