package com.example.karrotmarket.global.exception;

import com.example.karrotmarket.global.error.exception.KarrotException;
import com.example.karrotmarket.global.error.exception.ErrorCode;

public class FileNotFoundException extends KarrotException {
    public FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }
}
