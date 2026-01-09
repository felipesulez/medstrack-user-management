package com.medstrack.Medstrack.exception;

import com.medstrack.Medstrack.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de validación de campos (@NotBlank, @Email, @Size, etc.)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();

            // Si ya existe un error para este campo, concatena con "; "
            String existingMessage = fieldErrors.get(field);
            if (existingMessage == null) {
                fieldErrors.put(field, message);
            } else {
                fieldErrors.put(field, existingMessage + "; " + message);
            }
        });

        ApiError response = new ApiError(
                false,
                "VALIDATION_ERROR",
                "Datos inválidos en la solicitud",
                fieldErrors.isEmpty() ? null : fieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja JSON malformado o cuerpo de solicitud inválido (ej: texto plano en lugar de JSON)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleJsonParseError(HttpMessageNotReadableException ex) {
        ApiError response = new ApiError(
                false,
                "INVALID_JSON",
                "El formato JSON es inválido o el cuerpo de la solicitud no puede ser leído",
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja error de negocio: correo ya registrado
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        ApiError response = new ApiError(
                false,
                "BUSINESS_ERROR",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * Handler genérico para cualquier excepción no capturada específicamente
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericErrors(Exception ex) {
        ApiError response = new ApiError(
                false,
                "INTERNAL_ERROR",
                "Ocurrió un error inesperado en el servidor",
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}