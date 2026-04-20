package com.github.springboard1st.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
