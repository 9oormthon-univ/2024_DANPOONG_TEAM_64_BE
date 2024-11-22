package com.example.stocksbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Getter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String profileImage;
}
