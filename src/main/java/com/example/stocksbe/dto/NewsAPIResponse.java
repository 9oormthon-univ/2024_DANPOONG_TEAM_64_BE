package com.example.stocksbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsAPIResponse {

    private String status;
    private int totalResults;
    private List<NewsDTO> articles;
}
