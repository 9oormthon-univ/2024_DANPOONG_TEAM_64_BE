package com.example.stocksbe.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {
    HttpStatus getStatus();
    String getMessage();
}
