//package com.example.stocksbe.config;
//
//import com.example.stocksbe.auth.constant.SecurityConstants;
//import com.example.stocksbe.auth.filter.JwtFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        // cors disable
//        http.cors(cors -> cors
//                .configurationSource(CorsConfig.apiConfigurationSource()));
//
//        // csrf disable
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        // form 로그인 방식 disable
//        http.formLogin(AbstractHttpConfigurer::disable);
//
//        // http basic 인증 방식 disable
//        http.httpBasic(AbstractHttpConfigurer::disable);
//
//        // Session Stateless하게 관리
//        http.sessionManagement((session) -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        // 경로별 인가
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(HttpMethod.PATCH, "/api/v1//posts/{postId}").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/posts/{postId}/replies").hasAnyRole("ADMIN")
//                .requestMatchers(HttpMethod.PATCH, "/api/v1/replies/{replyId}").hasAnyRole("ADMIN")
//                .requestMatchers(SecurityConstants.allowedUrls).permitAll()
//                .anyRequest().authenticated()
//        );
//
//        http.exceptionHandling(
//                (configurer ->
//                        configurer
//                                .accessDeniedHandler(jwtAccessDeniedHandler)
//                )
//        );
//
//        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
//                UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new JwtFilter(jwtUtil, principalDetailsService),
//                LoginFilter.class);
//        UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new JwtExceptionFilter(), JwtFilter.class);
//
//        return http.build();
//    }
//}