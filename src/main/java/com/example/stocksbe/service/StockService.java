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
import java.util.Objects;

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

        StockResponseDTO response = restTemplate.getForObject(url, StockResponseDTO.class);

        if (response != null && response.getResults() != null) {
            List<StockResponseDTO.StockData> results = response.getResults();
            for (StockResponseDTO.StockData result : results) {
                Stock stock = new Stock();

                if(ticker.equals("APPL")){
                    stock.setStockName("APPLE");
                }
                if(ticker.equals("META")){
                    stock.setStockName("META");
                }
                if(ticker.equals("AMZN")){
                    stock.setStockName("Amazon");
                }
                if(ticker.equals("NVDA")){
                    stock.setStockName("NVIDIA");
                }
                if(ticker.equals("GOOGL")){
                    stock.setStockName("Alphabet A");
                }
                if(ticker.equals("MSFT")){
                    stock.setStockName("Microsoft");
                }

                stock.setStockTicker(ticker);

                stock.setDate(LocalDate.parse(date));

                stock.setAveragePrice(BigDecimal.valueOf(result.getAveragePrice()));
                stock.setOpenPrice(BigDecimal.valueOf(result.getOpenPrice()));
                stock.setClosePrice(BigDecimal.valueOf(result.getClosePrice()));
                stock.setCreatedAt(LocalDateTime.now());

                stockRepository.save(stock);
            }
        }

    }


}
