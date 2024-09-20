package com.chemi.lab.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    private String message;
    private String description;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}
