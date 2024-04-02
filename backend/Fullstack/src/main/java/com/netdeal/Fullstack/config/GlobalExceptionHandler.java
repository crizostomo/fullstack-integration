package com.netdeal.Fullstack.config;

import com.netdeal.Fullstack.dto.response.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleException(Exception e) {
        log.error("Global Exception Handler", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GlobalErrorResponse(e.getMessage()));
    }

}
