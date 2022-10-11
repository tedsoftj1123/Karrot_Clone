package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class NonLoginException extends KarrotException {
    public NonLoginException() {
        super(ErrorCode.NON_LOGIN);
    }
}
