package com.github.springboard1st.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expiration;

    /**
     * JWT 유틸리티 클래스 생성자
     * @param secret : 토큰 서명에 사용할 비밀키 (application.yml 에서 관리)
     * @param expiration : 토큰의 유효 시간 (ms 단위)
     */
    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    // 토큰 생성
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    // 토큰에서 이메일 추출
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
