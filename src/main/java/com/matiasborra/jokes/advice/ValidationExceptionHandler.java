package com.matiasborra.jokes.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para validaciones.
 * Captura y procesa errores de validación en los controladores.
 * Devuelve una respuesta estructurada con los detalles del error.
 *
 * @author Matias Borra
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Maneja excepciones de tipo MethodArgumentNotValidException.
     * Recolecta los errores de validación y los devuelve en un formato JSON.
     *
     * @param ex Excepción de validación capturada
     * @param request Objeto HttpServletRequest para obtener detalles de la solicitud
     * @return Respuesta con detalles de los errores y estado HTTP 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        // Recolectamos los errores de campo
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                fieldErrors.put(fe.getField(), fe.getDefaultMessage())
        );

        // Construimos el body con el formato deseado
        Map<String, Object> body = new LinkedHashMap<>();
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        body.put("timestamp", timestamp);
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("path", request.getRequestURI());
        body.put("errors", fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }
}