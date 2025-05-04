package com.matiasborra.jokes.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase ResponseHelper.
 * Proporciona métodos utilitarios para crear respuestas HTTP estándar en la aplicación.
 * Incluye métodos para generar respuestas de éxito y error.
 *
 * @author Matias
 */
public class ResponseHelper {

    /**
     * Crea una respuesta de error con un mensaje y un estado HTTP.
     *
     * @param message Mensaje de error
     * @param status  Estado HTTP de la respuesta
     * @return Respuesta HTTP con el mensaje de error y el estado
     */
    public static ResponseEntity<Object> createErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(Map.of("error", message, "status", status));
    }

    /**
     * Crea una respuesta de éxito con un payload.
     *
     * @param payload Datos a incluir en la respuesta
     * @return Respuesta HTTP con el payload y un estado HTTP 200 (OK)
     */
    public static ResponseEntity<Object> createSuccessResponse(Object payload) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.OK.value());
        body.put("data", payload);
        return ResponseEntity.ok(body);
    }
}