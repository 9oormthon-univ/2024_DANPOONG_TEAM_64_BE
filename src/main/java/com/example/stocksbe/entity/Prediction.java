package com.example.stocksbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "predictions")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate predictDate;

    @Column(nullable = false)
    private String predictStockTicker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PredictionType myPredict;

    @Enumerated(EnumType.STRING)
    @Column
    private PredictionType actualChange;

    @Column
    private Boolean myPredictionResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
