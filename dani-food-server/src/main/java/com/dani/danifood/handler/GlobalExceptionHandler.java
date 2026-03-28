package com.dani.danifood.handler;

import com.dani.danifood.exception.BaseException;
import com.dani.danifood.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Result<String>> handleRuntimeException(BaseException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // 这才是真正的HTTP 400
                .body(Result.error(e.getMessage()));
    }
}
