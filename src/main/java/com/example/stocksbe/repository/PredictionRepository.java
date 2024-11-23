package com.example.stocksbe.repository;

import com.example.stocksbe.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    boolean existsByUserIdAndPredictDate(Long userId, LocalDate now);
}
