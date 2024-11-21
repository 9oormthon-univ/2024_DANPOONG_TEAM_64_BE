package com.example.stocksbe.scheduler;

import com.example.stocksbe.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockScheduler {

    private final StockService stockService;

    @Scheduled(cron = "1 1 14 * * ?", zone = "Asia/Seoul") // 14:01:01
    public void stockGroup1() {
        List<String> tickers = List.of("AAPL", "META", "AMZN"); // APPLE, META, Amazon
        String marketDate = getMarketDate();

        System.out.println("StockGroup1 Scheduler 실행");

        for (String ticker : tickers) {
            stockService.getAndSaveStockData(ticker, marketDate);
        }
    }

    @Scheduled(cron = "1 2 14 * * ?", zone = "Asia/Seoul") // 14:02:01
    public void stockGroup2() {
        List<String> tickers = List.of("NVDA", "GOOGL", "MSFT"); // NVIDIA, Alphabet A, Microsoft
        String marketDate = getMarketDate();

        System.out.println("StockGroup2 Scheduler 실행");

        for (String ticker : tickers) {
            stockService.getAndSaveStockData(ticker, marketDate);
        }
    }

    public String getMarketDate() {  // 미국 시장 기준 날짜 변환
        ZonedDateTime nowKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        ZonedDateTime yesterdayKST = nowKST.minusDays(1);
        ZonedDateTime marketTimeEST = yesterdayKST.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDate marketDate = marketTimeEST.toLocalDate();

        return marketDate.format(DateTimeFormatter.ISO_DATE);
    }
}
