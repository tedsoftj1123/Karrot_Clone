package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class DealRequestAlreadyExistsException extends BaseException {
    public DealRequestAlreadyExistsException() {
        super(ErrorCode.DEAL_REQUEST_ALREADY_EXISTS);
    }
}
