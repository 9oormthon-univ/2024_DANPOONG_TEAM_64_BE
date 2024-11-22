package com.example.stocksbe.dto;

import com.example.stocksbe.entity.PredictionType;

public record PredictionRequestDTO(
        String predictStockTicker,
        PredictionType myPredict
) {
}
