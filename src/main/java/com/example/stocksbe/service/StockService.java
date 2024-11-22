package com.example.stocksbe.service;

import com.example.stocksbe.dto.PredictionRequestDTO;
import com.example.stocksbe.dto.PredictionResponseDTO;
import com.example.stocksbe.dto.StockDataDTO;
import com.example.stocksbe.dto.StockResponseDTO;
import com.example.stocksbe.entity.Prediction;
import com.example.stocksbe.entity.Stock;
import com.example.stocksbe.entity.User;
import com.example.stocksbe.exception.GeneralException;
import com.example.stocksbe.exception.PredictionException;
import com.example.stocksbe.repository.PredictionRepository;
import com.example.stocksbe.repository.StockRepository;
import com.example.stocksbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final PredictionRepository predictionRepository;

    private final RestTemplate restTemplate;

    @Value("${POLYGON_API_KEY}")
    private String apiKey;

    public void getAndSaveStockData(String ticker, String date){

        String url = String.format(
                "https://api.polygon.io/v2/aggs/ticker/%s/range/1/day/%s/%s?adjusted=true&sort=asc&apiKey=%s",
                ticker, date, date, apiKey
        );

        try {
            StockDataDTO response = restTemplate.getForObject(url, StockDataDTO.class);

            if (response == null || response.results() == null || response.results().isEmpty()) {
                return;
            }

            List<StockDataDTO.StockData> results = response.results();
            for (StockDataDTO.StockData result : results) {
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
                stock.setAveragePrice(BigDecimal.valueOf(result.averagePrice()));
                stock.setOpenPrice(BigDecimal.valueOf(result.openPrice()));
                stock.setClosePrice(BigDecimal.valueOf(result.closePrice()));
                stock.setCreatedAt(LocalDateTime.now());

                stockRepository.save(stock);
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "API 요청 권한 없음, 전날 데이터는 14시 이후에 호출 가능");
            }
            throw e;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StockResponseDTO getStock(String ticker){
        LocalDate period = LocalDate.now().minusDays(30);
        List<Stock> stocks = stockRepository.findByStockTickerAndDateAfter(ticker, period);

        if (stocks.isEmpty()) {
            return null;
        }

        Stock stock = stocks.get(0);

        List<StockResponseDTO.dailyStockData> dailyResults = stocks.stream()
                .map(s -> new StockResponseDTO.dailyStockData(
                        s.getDate(),
                        s.getAveragePrice(),
                        s.getOpenPrice(),
                        s.getClosePrice()
                ))
                .toList();

        return new StockResponseDTO(
                stock.getStockName(),
                stock.getStockTicker(),
                dailyResults
        );
    }

    public PredictionResponseDTO createPrediction(PredictionRequestDTO requestDTO, Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        boolean alreadyPredicted = predictionRepository.existsByUserIdAndPredictDate(userId, LocalDate.now());
        if (alreadyPredicted) {
            throw new GeneralException(PredictionException.ALREADY_EXIST);
        }

        Prediction prediction = new Prediction();
        prediction.setPredictDate(LocalDate.now());
        prediction.setPredictStockTicker(requestDTO.predictStockTicker());
        prediction.setMyPredict(requestDTO.myPredict());
        prediction.setUser(user);

        predictionRepository.save(prediction);

        return new PredictionResponseDTO(
                user.getId(),
                prediction.getPredictDate(),
                prediction.getPredictStockTicker(),
                prediction.getMyPredict()
        );
    }
}
