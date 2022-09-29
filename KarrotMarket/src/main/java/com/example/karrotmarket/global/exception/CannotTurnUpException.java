package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class CannotTurnUpException extends KarrotException {
    public CannotTurnUpException() {
        super(ErrorCode.CANNOT_TURN_UP);
    }
}
