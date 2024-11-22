package com.example.stocksbe.controller;

import com.example.stocksbe.dto.StockResponseDTO;
import com.example.stocksbe.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/stocks/{ticker}")
    public ResponseEntity<StockResponseDTO> getStockByTicker(
            @PathVariable String ticker){
        StockResponseDTO responseDTO = stockService.getStock(ticker);

        if (responseDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }
}
