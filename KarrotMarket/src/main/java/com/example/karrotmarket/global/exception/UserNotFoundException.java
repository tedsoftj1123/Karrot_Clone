package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class UserNotFoundException extends KarrotException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
