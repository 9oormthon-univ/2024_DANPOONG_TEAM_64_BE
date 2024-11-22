package com.example.stocksbe.repository;

import com.example.stocksbe.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByStockTickerAndDateAfter(String ticker, LocalDate period);
}
