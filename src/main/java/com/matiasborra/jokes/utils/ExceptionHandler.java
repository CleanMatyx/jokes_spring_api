package com.matiasborra.jokes.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

/**
 * Clase ExceptionHandler.
 * Maneja excepciones globales en la aplicación, proporcionando respuestas personalizadas.
 *
 * @author Matias
 */
@ControllerAdvice
public class ExceptionHandler {

    /**
     * Maneja excepciones de tipo RuntimeException.
     *
     * @param ex Excepción lanzada
     * @return Respuesta con el mensaje de error y el estado HTTP correspondiente
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage(), "status", HttpStatus.NOT_FOUND));
    }
}