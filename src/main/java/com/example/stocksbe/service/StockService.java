package com.example.stocksbe.service;

import com.example.stocksbe.dto.StockResponseDTO;
import com.example.stocksbe.entity.Stock;
import com.example.stocksbe.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final RestTemplate restTemplate;

    @Value("${POLYGON_API_KEY}")
    private String apiKey;

    public void getAndSaveStockData(String ticker, String date){

        String url = String.format(
                "https://api.polygon.io/v2/aggs/ticker/%s/range/1/day/%s/%s?adjusted=true&sort=asc&apiKey=%s",
                ticker, date, date, apiKey
        );

        try {
            StockResponseDTO response = restTemplate.getForObject(url, StockResponseDTO.class);

            if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
                return;
            }

            List<StockResponseDTO.StockData> results = response.getResults();
            for (StockResponseDTO.StockData result : results) {
                Stock stock = new Stock();

                switch (ticker) {
                    case "AAPL" -> stock.setStockName("Apple");
                    case "META" -> stock.setStockName("Meta");
                    case "AMZN" -> stock.setStockName("Amazon");
                    case "NVDA" -> stock.setStockName("NVIDIA");
                    case "GOOGL" -> stock.setStockName("Alphabet A");
                    case "MSFT" -> stock.setStockName("Microsoft");
                    default -> stock.setStockName("Unknown");
                }

                stock.setStockTicker(ticker);
                stock.setDate(LocalDate.parse(date));
                stock.setAveragePrice(BigDecimal.valueOf(result.getAveragePrice()));
                stock.setOpenPrice(BigDecimal.valueOf(result.getOpenPrice()));
                stock.setClosePrice(BigDecimal.valueOf(result.getClosePrice()));
                stock.setCreatedAt(LocalDateTime.now());

                stockRepository.save(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
