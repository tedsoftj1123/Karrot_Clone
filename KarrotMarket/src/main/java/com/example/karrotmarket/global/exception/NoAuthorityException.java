package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class NoAuthorityException extends KarrotException {
    public NoAuthorityException() {
        super(ErrorCode.NO_AUTHORITY);
    }
}
