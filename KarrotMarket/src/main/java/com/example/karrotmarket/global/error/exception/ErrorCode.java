package com.example.karrotmarket.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User not fund"),

    ITEM_NOT_EXISTS(404, "Item not Exists"),

    CANNOT_NEGOTIATE(409, "This item cannot be negotiated"),

    INVALID_JWT(401, "Invalid jwt"),
    EXPIRED_JWT(401, "Expired Jwt"),
    EXPIRED_REFRESH(401, "RefreshToken Expired, please login again"),
    WRONG_PASSWORD(401, "Wrong password"),

    NON_LOGIN(401, "User not login"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken not found"),

    FILE_NOT_FOUND(404, "File not found"),
    DEAL_REQUEST_NOT_FOUND(404, "DealRequest not found"),
    DEAL_REQUEST_ALREADY_EXISTS(409, "Deal Request cannot be duplicated"),
    NO_AUTHORITY(403, "Cannot access"),
    CANNOT_TURN_UP(400, "Cant turnUp Item too often")
    ;

    private final int status;
    private final String message;
}
