package com.github.springboard1st.domain.post.dto;

import com.github.springboard1st.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private long id;
    private String title;
    private String content;
    private String email;
    private LocalDateTime createdAt;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.email = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt();
    }
}
