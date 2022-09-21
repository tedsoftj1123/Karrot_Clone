package com.example.karrotmarket.global.error;

import com.example.karrotmarket.global.error.exception.BaseException;
import com.example.karrotmarket.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorResponse> handleException(BaseException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .timestamp(ZonedDateTime.now())
                        .build(),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException e) {
        List<String> fieldErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError :: getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(400, fieldErrors.toString(), ZonedDateTime.now()), HttpStatus.valueOf(400));
    }
}
