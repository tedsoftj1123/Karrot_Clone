package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class WrongPasswordException extends KarrotException {
    public WrongPasswordException() {
        super(ErrorCode.WRONG_PASSWORD);
    }
}
