package com.example.stocksbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsSchedulerService {

    private final NewsService newsService;

    // 매일 오후 22시에 실행
    @Scheduled(cron = "0 0 22 * * *")
    public void fetchDailyNews() {
        // 6개의 주식 종목
        List<String> stockSymbols = List.of("APPLE", "META", "Amazon", "NVIDIA", "Alphabet A", "Microsoft");

        // 각 종목에 대해 뉴스 가져오기 및 저장
        stockSymbols.forEach(stock -> {
            try {
                newsService.fetchAndSaveNews(stock);
                System.out.println(stock + " 뉴스 저장 완료");
            } catch (Exception e) {
                System.err.println(stock + " 뉴스 저장 중 오류: " + e.getMessage());
            }
        });
    }
}