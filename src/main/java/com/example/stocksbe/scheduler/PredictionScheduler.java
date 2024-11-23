package com.example.stocksbe.scheduler;

import com.example.stocksbe.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PredictionScheduler {

    private final PredictionService predictionService;

    @Scheduled(cron = "0 5 14 * * ?") // 매일 오후 14시 5분에 실행(주식 데이터를 미국장 기준으로 가져오기 때문)
    public void schedulePredictionUpdate() {
        LocalDate today = LocalDate.now();
        predictionService.updatePredictionsWithResults(today);
    }
}
