package com.github.springboard1st.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequest {

    @NotBlank
    private String content;

}
