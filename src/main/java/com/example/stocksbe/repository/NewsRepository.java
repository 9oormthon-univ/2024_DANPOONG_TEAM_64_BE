package com.example.stocksbe.repository;

import com.example.stocksbe.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    Optional<News> findByTitleAndStockName(String title, String stockName);
    List<News> findByStockName(String stockName);
}
