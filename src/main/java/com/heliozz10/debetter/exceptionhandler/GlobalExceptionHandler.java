package com.heliozz10.debetter.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    private ResponseEntity<ApiErrorResponse> buildError(HttpStatus status, String message, WebRequest request) {
//        return ResponseEntity.status(status).body(
//                ApiErrorResponse.of(
//                        status.value(),
//                        status.getReasonPhrase(),
//                        message,
//                        request.getDescription(false).replace("uri=", "")
//                )
//        );
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiErrorResponse> handleValidationErrors(
//            MethodArgumentNotValidException ex, WebRequest request
//    ) {
//        String message = ex.getBindingResult().getFieldErrors().stream()
//                .map(err -> err.getField() + ": " + err.getDefaultMessage())
//                .findFirst()
//                .orElse("Validation failed");
//        return buildError(HttpStatus.BAD_REQUEST, message, request);
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<ApiErrorResponse> handleIllegalState(
//            IllegalStateException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(
//            ConstraintViolationException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<ApiErrorResponse> handleMissingParam(
//            MissingServletRequestParameterException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
//    }
//
//    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
//    public ResponseEntity<ApiErrorResponse> handleNotFound(
//            RuntimeException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiErrorResponse> handleDataIntegrity(
//            DataIntegrityViolationException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.CONFLICT, "Database constraint violation", request);
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiErrorResponse> handleAuth(
//            AuthenticationException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.UNAUTHORIZED, "Authentication failed", request);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiErrorResponse> handleAccessDenied(
//            AccessDeniedException ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.FORBIDDEN, "Access denied", request);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiErrorResponse> handleGeneral(
//            Exception ex, WebRequest request
//    ) {
//        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request);
//    }
}
