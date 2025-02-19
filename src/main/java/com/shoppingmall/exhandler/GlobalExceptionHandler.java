package com.shoppingmall.exhandler;

import com.shoppingmall.user.exception.DuplicateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 전역에서 발생하는 모든 예외를 한곳에서 처리할 수 있게 도와주는 class
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 중복 예외 핸들러
    // Duplicate 예외는 전부 여기서 처리 가능
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Map<String , Object>> DuplicateExceptionHandler(DuplicateException e) {
        Map<String , Object> response = new HashMap<>();
        response.put("status", e.getErrors());
        return ResponseEntity.badRequest().body(response);
    }


}
