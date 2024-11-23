package com.example.stocksbe.controller;

import com.example.stocksbe.dto.LoginResponse;
import com.example.stocksbe.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final KakaoService kakaoService;

    //web 버전
    @GetMapping("/login/oauth/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestParam String code) {
        try {
            return ResponseEntity.ok(kakaoService.kakaoLogin(code));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }
}