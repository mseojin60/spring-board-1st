package com.github.springboard1st.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignupRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
