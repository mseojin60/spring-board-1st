package com.github.springboard1st.domain.user;

import com.github.springboard1st.domain.user.dto.LoginRequest;
import com.github.springboard1st.domain.user.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token= userService.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // JWT는 서버에상태 없음, 클라이언트에서 토큰 삭제
        return ResponseEntity.ok("로그아웃 성공");
    }
}
