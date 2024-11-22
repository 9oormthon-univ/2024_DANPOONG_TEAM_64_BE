package com.example.stocksbe.dto;

import com.example.stocksbe.entity.PredictionType;

import java.time.LocalDate;

public record PredictionResponseDTO(
        Long userId,
        LocalDate date,
        String predictStockTicker,
        PredictionType myPredict
) {
}
