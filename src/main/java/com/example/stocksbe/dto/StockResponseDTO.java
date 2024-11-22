package com.example.stocksbe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockResponseDTO(
        String stockName,
        String StockTicker

) {

    public record dailyStockData(
            LocalDate date,
            BigDecimal averagePrice,
            BigDecimal openPrice,
            BigDecimal closePrice
    ){}
}
