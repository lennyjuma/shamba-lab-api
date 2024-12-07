package com.chemi.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiResourceNotFoundException.class)
    public ResponseEntity<Object> handleException(ApiResourceNotFoundException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getLocalizedMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC"))
        );
        return ResponseEntity.status(badRequest).body(apiException);
    }

}
