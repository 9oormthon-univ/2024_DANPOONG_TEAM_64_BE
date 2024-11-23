package com.example.stocksbe.repository;

import com.example.stocksbe.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.stockTicker = :ticker AND s.date >= :date")
    List<Stock> findByStockTickerAndDateAfter(@Param("ticker") String stockTicker, @Param("date") LocalDate date);

    // 특정 종목 티커와 날짜에 해당하는 종가를 가져오는 메서드
    Optional<Stock> findByStockTickerAndDate(String stockTicker, LocalDate date);
}
