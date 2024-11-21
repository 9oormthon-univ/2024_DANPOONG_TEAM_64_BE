package com.example.stocksbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StocksBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StocksBeApplication.class, args);
    }

}
