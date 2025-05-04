package com.matiasborra.jokes.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper {

    public static ResponseEntity<Object> createErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(Map.of("error", message, "status", status));
    }

    public static ResponseEntity<Object> createSuccessResponse(Object payload) {
        Map<String,Object> body = new HashMap<>();
        body.put("status", HttpStatus.OK.value());
        body.put("data", payload);
        return ResponseEntity.ok(body);
    }
}
