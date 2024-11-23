package com.example.stocksbe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="id")
    private Long uid; //유저 기본키

    private String nickname; //닉네임

    @Column(unique = true)
    private String email; //이메일

    private String password; //비밀번호

    @Column(name="created_at")
    private LocalDateTime createdAt; // 회원가입 시간

    @Column(name="profile_image")
    private String profileImg; // 프로필 이미지 url
}