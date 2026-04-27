package com.github.springboard1st.config;

import com.github.springboard1st.jwt.JwtAuthFilter;
import com.github.springboard1st.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration              // 설정 클래스
@EnableWebSecurity          // Spring Security 활성화
@RequiredArgsConstructor    // JwtUtil 생성자 주입
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of(
                            "http://localhost:5174",
                            "https://spring-board-front-1st-1s8o-5rll2j52j-mseojin60s-projects.vercel.app"
                    ));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable()) // csrf 공격 방어 기능 해제. -> REST API + JWT 방식에서는 필요없음
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 서버가 세션을 만들지 않음
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/index.html", "/").permitAll()  // 프론트 정적 파일 허용
                        .requestMatchers("/api/auth/**").permitAll() // 로그인/회원가입 누구나
                        .requestMatchers("/api/posts").permitAll()   // 게시물 조회 누구나
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // swagger 누구나
                        .anyRequest().authenticated()   // 나머지는 토큰 필요
                )
                .addFilterBefore(new JwtAuthFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class);    // Spring Security 기본 필터 앞에 JWT 필터 끼워넣음

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }   // 비밀번호 암호화.
}
