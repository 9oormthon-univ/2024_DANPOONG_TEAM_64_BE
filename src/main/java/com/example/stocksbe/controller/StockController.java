package com.example.stocksbe.controller;

import com.example.stocksbe.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/stocks/save")
    public ResponseEntity<String> fetchAndSaveStockData(
            @RequestParam String ticker,
            @RequestParam String date
    ) {
        stockService.getAndSaveStockData(ticker, date);
        return ResponseEntity.ok(ticker + " 종목의 " + date + " 데이터를 저장");
    }

}
