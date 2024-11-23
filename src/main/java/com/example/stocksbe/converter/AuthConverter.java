//package com.example.stocksbe.converter;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class AuthConverter {
//    public static User toUser(String email, String name, String password, PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                .build();
//    }
//}