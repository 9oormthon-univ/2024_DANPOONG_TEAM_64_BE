package com.example.stocksbe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record StockResponseDTO(
        String stockName,
        String StockTicker,
        List<dailyStockData> dailyResults

) {

    public record dailyStockData(
            LocalDate date,
            BigDecimal averagePrice,
            BigDecimal openPrice,
            BigDecimal closePrice
    ){}
}
