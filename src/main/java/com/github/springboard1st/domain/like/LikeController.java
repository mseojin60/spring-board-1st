package com.github.springboard1st.domain.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeRepository likeRepository;
    private final LikeService likeService;

    // 좋아요
    @PostMapping("/{postId}")
    public ResponseEntity<?> like (
            @PathVariable Long postId,
            @AuthenticationPrincipal String email) {
        likeService.like(postId, email);
        return ResponseEntity.ok("좋아요 성공");
    }

    // 좋아요 취소
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> unlike (
            @PathVariable Long postId,
            @AuthenticationPrincipal String email) {
        likeService.unlike(postId, email);
        return ResponseEntity.ok("좋아요 취소 성공");
    }
}
