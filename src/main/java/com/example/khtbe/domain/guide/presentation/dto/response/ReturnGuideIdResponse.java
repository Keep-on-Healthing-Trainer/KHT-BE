package com.example.khtbe.domain.guide.presentation.dto.response;

import lombok.Getter;

@Getter
public class ReturnGuideIdResponse {
    private Long id;
    public ReturnGuideIdResponse(Long id) {
        this.id = id;
    }

}
