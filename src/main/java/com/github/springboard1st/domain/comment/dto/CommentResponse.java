package com.github.springboard1st.domain.comment.dto;

import com.github.springboard1st.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private String email;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.email = comment.getUser().getEmail();
        this.createdAt = comment.getCreatedAt();
    }
}
