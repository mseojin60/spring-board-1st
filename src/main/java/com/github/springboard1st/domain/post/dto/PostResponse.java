package com.github.springboard1st.domain.post.dto;

import com.github.springboard1st.domain.comment.dto.CommentResponse;
import com.github.springboard1st.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String email;
    private LocalDateTime createdAt;
    private int likeCount;
    private boolean liked;
    private List<CommentResponse> comments;

    public PostResponse(Post post, int likeCount, boolean liked, List<CommentResponse> comments) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.email = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt();
        this.likeCount = likeCount;
        this.liked = liked;
        this.comments = comments;
    }
}
