package com.example.stocksbe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "news")
@Entity
@Data
@RequiredArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_name", nullable = false, length = 30)
    private String stockName;

    @Column(name = "title", nullable = false)
    private String title;

    private String author;

    @Column(nullable = false)
    private String url;

    @Column(name = "url_to_image", nullable = false, length = 255)
    private String urlToImage;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt; // DB 저장 시간
}