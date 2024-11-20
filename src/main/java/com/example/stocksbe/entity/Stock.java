package com.example.stocksbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "stocks")
@Entity
@Getter
@RequiredArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String stockName;

    @Column(nullable = false)
    private String stockTicker;

    @Column(nullable = false)
    private LocalDate date; // 주식 시장 날짜

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal averagePrice;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal openPrice;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal closePrice;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt; // DB 저장 시
}
