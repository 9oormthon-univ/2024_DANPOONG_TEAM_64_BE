package com.example.stocksbe.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PredictionException implements BaseException{

    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 예측을 완료하였습니다. 예측은 하루에 한번만 가능합니다.");

    private final HttpStatus status;
    private final String message;
}
