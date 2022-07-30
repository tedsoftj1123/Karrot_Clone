package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class ItemNotExistsException extends BaseException {
    public ItemNotExistsException() {
        super(ErrorCode.ITEM_NOT_EXISTS);
    }
}
