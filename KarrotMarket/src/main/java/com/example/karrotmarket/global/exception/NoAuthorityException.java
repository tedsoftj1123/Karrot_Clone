package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class NoAuthorityException extends BaseException {
    public NoAuthorityException() {
        super(ErrorCode.NO_AUTHORITY);
    }
}
