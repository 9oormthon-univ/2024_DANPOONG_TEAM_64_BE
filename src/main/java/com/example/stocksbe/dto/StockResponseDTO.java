package com.example.stocksbe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Data
public class StockResponseDTO {

    private String ticker;
    private List<StockData> results;

    @Data
    public static class StockData {
        @JsonProperty("t")
        private Long timestamp;
        @JsonProperty("c")
        private Double closePrice;
        @JsonProperty("o")
        private Double openPrice;
        @JsonProperty("vw")
        private Double averagePrice;

        public LocalDate getDate() {
            return Instant.ofEpochMilli(timestamp)
                    .atZone(ZoneId.of("Asia/Seoul"))
                    .toLocalDate();
        }
    }
}
