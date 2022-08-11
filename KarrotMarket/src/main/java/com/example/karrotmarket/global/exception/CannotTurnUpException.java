package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class CannotTurnUpException extends BaseException {
    public CannotTurnUpException() {
        super(ErrorCode.CANNOT_TURN_UP);
    }
}
