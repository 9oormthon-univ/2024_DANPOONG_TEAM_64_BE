package com.example.stocksbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    private String stockName;   // 주식 종목
    private String title;       // 기사 제목
    private String author;      // 작성 기자
    private String url;         // 기사 링크
    private String UrlToImage;  // 기사 사진 링크

}
