package com.example.stocksbe.service;

import com.example.stocksbe.dto.NewsAPIResponse;
import com.example.stocksbe.dto.NewsDTO;
import com.example.stocksbe.entity.News;
import com.example.stocksbe.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    @Value("${NEWS_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;

    // 특정 종목의 뉴스를 DTO로 변환하여 반환
    public List<NewsDTO> getNewsByStockName(String stockName) {
        List<News> newsList = newsRepository.findByStockName(stockName);
        return newsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 모든 뉴스 가져오기
    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // News 엔티티를 NewsDTO로 변환하는 메서드
    private NewsDTO convertToDTO(News news) {
        return NewsDTO.builder()
                .stockName(news.getStockName())
                .title(news.getTitle())
                .author(news.getAuthor())
                .url(news.getUrl())
                .UrlToImage(news.getUrlToImage())
                .build();
    }

    // 뉴스 데이터 가져오기 및 저장
    public void fetchAndSaveNews(String stockName) {
        String url = String.format(
                "https://newsapi.org/v2/everything?q=%s&apiKey=%s",
                stockName,
                apiKey
        );

        // News API 호출
        NewsAPIResponse response = restTemplate.getForObject(url, NewsAPIResponse.class);
        if (response != null && response.getArticles() != null) {
            // DTO -> Entity 변환 후 저장
            List<News> newsEntities = response.getArticles().stream()
                    .filter(article -> article.getTitle() != null && !article.getTitle().contains("[Removed]") && article.getUrlToImage() != null)
                    .map(dto -> convertDtoToEntity(dto, stockName)) // 종목 이름 추가
                    .filter(this::isNewNews) // 중복 체크
                    .collect(Collectors.toList());

            newsRepository.saveAll(newsEntities); // 새 데이터만 저장
        }
    }

    // 중복 체크
    private boolean isNewNews(News news) {
        return newsRepository.findByTitleAndStockName(news.getTitle(), news.getStockName()).isEmpty();
    }

    // DTO -> Entity 변환
    private News convertDtoToEntity(NewsDTO dto, String stockName) {
        News news = new News();
        news.setStockName(stockName);
        news.setTitle(dto.getTitle());
        news.setAuthor(dto.getAuthor());
        news.setUrl(dto.getUrl());
        news.setUrlToImage(dto.getUrlToImage());
        return news;
    }
}
