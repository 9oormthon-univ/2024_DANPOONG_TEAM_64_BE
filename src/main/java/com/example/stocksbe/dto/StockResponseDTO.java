package com.example.stocksbe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StockResponseDTO(
        String ticker,
        List<StockData> results
) {
    public record StockData(
            @JsonProperty("t") Long timestamp,
            @JsonProperty("c") Double closePrice,
            @JsonProperty("o") Double openPrice,
            @JsonProperty("vw") Double averagePrice
    ) {}
}
