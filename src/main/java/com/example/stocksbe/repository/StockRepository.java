package com.example.stocksbe.repository;

import com.example.stocksbe.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.stockTicker = :ticker AND s.date >= :date")
    List<Stock> findByStockTickerAndDateAfter(@Param("ticker") String stockTicker, @Param("date") LocalDate date);
}
