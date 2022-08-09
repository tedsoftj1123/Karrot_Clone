package com.example.karrotmarket.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User not fund"),
    TOKEN_INVALID(401, "Token is Invalid"),

    ITEM_NOT_EXISTS(404, "Item not Exists"),

    CANNOT_NEGOTIATE(409, "This item cannot be negotiated"),

    INVALID_JWT(401, "Invalid jwt"),
    EXPIRED_JWT(401, "Expired Jwt"),
    WRONG_PASSWORD(401, "Wrong password"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken not found"),

    FILE_NOT_FOUND(404, "File not found"),
    DEAL_REQUEST_NOT_FOUND(404, "DealRequest not found"),
    DEAL_REQUEST_ALREADY_EXISTS(409, "Deal Request cannot be duplicated")
    ;

    private final int status;
    private final String message;
}
