package com.example.stocksbe.controller;

import com.example.stocksbe.dto.NewsDTO;
import com.example.stocksbe.dto.PredictionRequestDTO;
import com.example.stocksbe.dto.PredictionResponseDTO;
import com.example.stocksbe.dto.StockResponseDTO;
import com.example.stocksbe.entity.News;
import com.example.stocksbe.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    // 특정 주제의 뉴스 반환
    @GetMapping("/{stockName}")
    public ResponseEntity<List<NewsDTO>> getNewsByStockName(@PathVariable String stockName) {
        List<NewsDTO> newsList = newsService.getNewsByStockName(stockName);
        return ResponseEntity.ok(newsList);
    }

    // 모든 뉴스 반환
    @GetMapping("/allNews")
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }
}
