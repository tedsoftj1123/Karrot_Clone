package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class DealRequestAlreadyExistsException extends KarrotException {
    public DealRequestAlreadyExistsException() {
        super(ErrorCode.DEAL_REQUEST_ALREADY_EXISTS);
    }
}
