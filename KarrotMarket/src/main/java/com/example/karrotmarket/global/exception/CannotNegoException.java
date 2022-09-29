package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class CannotNegoException extends KarrotException {
    public CannotNegoException() {
        super(ErrorCode.CANNOT_NEGOTIATE);
    }
}
