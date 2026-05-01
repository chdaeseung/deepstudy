package com.deepstudy.global.exception;

import com.deepstudy.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {

        ErrorResponse response = new ErrorResponse(false, e.getErrorCode().getCode(), e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
