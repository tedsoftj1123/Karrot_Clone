package com.example.karrotmarket.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User not fund"),
    TOKEN_INVALID(401, "Token is Invalid"),

    ITEM_NOT_EXISTS(404, "Item not Exists")
    ;

    private final int status;
    private final String message;
}
