package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class WrongPasswordException extends BaseException {
    public WrongPasswordException() {
        super(ErrorCode.WRONG_PASSWORD);
    }
}
