package com.github.springboard1st.domain.post;

import com.github.springboard1st.domain.post.dto.PostRequest;
import com.github.springboard1st.domain.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(
            @RequestParam(required = false) String email,
            @AuthenticationPrincipal UserDetails userDetails) {
        String loginEmail = userDetails != null ? userDetails.getUsername() : null;
        return ResponseEntity.ok(postService.getAllPosts(loginEmail));
    }

    // 게시물 생성
    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestBody PostRequest request,
            @AuthenticationPrincipal String email) {
        postService.createPost(request, email);
        return ResponseEntity.ok("게시물 생성 성공");
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequest request,
            @AuthenticationPrincipal String email) {
        postService.updatePost(id, request, email);
        return ResponseEntity.ok("게시물 수정 성공");
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal String email) {
        postService.deletePost(id, email);
        return ResponseEntity.ok("게시물 삭제 성공");
    }
}
