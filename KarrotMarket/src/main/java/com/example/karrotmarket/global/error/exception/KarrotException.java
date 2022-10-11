package com.example.karrotmarket.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KarrotException extends RuntimeException{
    private final ErrorCode errorCode;
}
