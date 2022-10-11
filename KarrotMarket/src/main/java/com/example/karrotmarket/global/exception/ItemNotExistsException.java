package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class ItemNotExistsException extends KarrotException {
    public ItemNotExistsException() {
        super(ErrorCode.ITEM_NOT_EXISTS);
    }
}
