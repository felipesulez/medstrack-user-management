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
     * Handles validation errors from @NotBlank, @Email, @Size, etc.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();

            // If the field already has an error, concatenate messages with "; "
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
                "Invalid request data",
                fieldErrors.isEmpty() ? null : fieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles malformed JSON or an invalid request body.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleJsonParseError(HttpMessageNotReadableException ex) {
        ApiError response = new ApiError(
                false,
                "INVALID_JSON",
                "Invalid JSON format or request body could not be read",
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles the business error when an email is already registered.
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
     * Handles unexpected exceptions that are not explicitly handled.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericErrors(Exception ex) {
        ApiError response = new ApiError(
                false,
                "INTERNAL_ERROR",
                "An unexpected error occurred on the server",
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}