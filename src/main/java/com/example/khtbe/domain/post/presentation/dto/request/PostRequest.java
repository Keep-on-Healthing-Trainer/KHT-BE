package com.example.khtbe.domain.post.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PostRequest {

    @NotBlank
    @Size(min = 1, max = 25)
    private String title;

    @NotBlank
    @Size(min = 1, max = 2000)
    private String content;
}
