package com.example.stocksbe.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class GeneralException extends IllegalArgumentException {

    private final BaseException exception;

    public HttpStatus getStatus() {

        return exception.getStatus();
    }

    public String getMessage() {

        return exception.getMessage();
    }
}
