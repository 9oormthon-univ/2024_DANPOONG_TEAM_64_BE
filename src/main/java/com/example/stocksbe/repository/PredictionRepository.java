package com.example.stocksbe.repository;

import com.example.stocksbe.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    boolean existsByUserUidAndPredictDate(Long userUid, LocalDate now);
    List<Prediction> findByPredictDate(LocalDate date);

}
