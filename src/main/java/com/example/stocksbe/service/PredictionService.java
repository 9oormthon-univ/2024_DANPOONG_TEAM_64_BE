package com.example.stocksbe.service;

import com.example.stocksbe.entity.Prediction;
import com.example.stocksbe.entity.PredictionType;
import com.example.stocksbe.entity.Stock;
import com.example.stocksbe.entity.User;
import com.example.stocksbe.repository.PredictionRepository;
import com.example.stocksbe.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PredictionRepository predictionRepository;
    private final StockRepository stockRepository;

    @Transactional
    public void updatePredictionsWithResults(LocalDate date) {
        // 1. 해당 날짜에 예측한 모든 Prediction 가져오기
        List<Prediction> predictions = predictionRepository.findByPredictDate(date.minusDays(1));

        // 2. 각 예측에 대해 실제 결과를 계산하고 업데이트
        for (Prediction prediction : predictions) {
            String stockTicker = prediction.getPredictStockTicker();

            // 2.1. 실제 종가 데이터 가져오기
            double previousClose = getClosePrice(stockTicker, date.minusDays(1));
            double currentClose = getClosePrice(stockTicker, date);

            // 2.2. 등락 계산
            PredictionType actualChange = calculateActualChange(previousClose, currentClose);
            prediction.setActualChange(actualChange);

            // 2.3. 예측 결과 비교
            boolean isPredictionCorrect = prediction.getMyPredict().equals(actualChange);
            prediction.setMyPredictionResult(isPredictionCorrect);

            // 2.4. 업데이트
            predictionRepository.save(prediction);
        }
    }

    // 종가를 DB에서 조회하는 메서드
    private double getClosePrice(String stockTicker, LocalDate date) {
        // 주식 티커와 날짜에 해당하는 종가를 DB에서 가져옴
        Stock stock = stockRepository.findByStockTickerAndDate(stockTicker, date)
                .orElseThrow(() -> new RuntimeException("Stock not found for the given date and ticker"));

        // 종가를 반환
        return stock.getClosePrice().doubleValue();
    }

    private PredictionType calculateActualChange(double previousClose, double currentClose) {
        if (currentClose > previousClose) {
            return PredictionType.UP;
        } else if (currentClose < previousClose) {
            return PredictionType.DOWN;
        } else {
            return PredictionType.NO_CHANGE;
        }
    }

}
