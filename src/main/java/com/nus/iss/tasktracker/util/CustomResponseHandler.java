package com.nus.iss.tasktracker.util;

import com.nus.iss.tasktracker.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class CustomResponseHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        log.error(ex.getLocalizedMessage());
        Response errorResponse = new Response(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Failed",
                new Object[]{ex.getMessage()}
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more exception handlers if needed

    // Success response handler
    public static ResponseEntity<Response> handleSuccessResponse(Object body, HttpStatus status, String message) {
        Response successResponse = new Response(
                LocalDateTime.now(),
                status.value(),
                "",
                message,
                body
        );

        return new ResponseEntity<>(successResponse, status);
    }

    public static ResponseEntity<Response> handleFailResponse(Object body, HttpStatus status, String message) {
        Response successResponse = new Response(
                LocalDateTime.now(),
                status.value(),message,
                "",
                body
        );

        return new ResponseEntity<>(successResponse, status);
    }
}
