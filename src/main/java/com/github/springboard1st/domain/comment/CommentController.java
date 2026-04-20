package com.github.springboard1st.domain.comment;

import com.github.springboard1st.domain.comment.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/{postId}")
    public ResponseEntity<?> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal String email) {
        commentService.createComment(postId, request, email);
        return ResponseEntity.ok("댓글 생성 성공");
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal String email) {
        commentService.updateComment(id, request, email);
        return ResponseEntity.ok("댓글 수정 성공");
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal String email){
        commentService.deleteComment(id, email);
        return ResponseEntity.ok("댓글 삭제 성공");
    }
}
