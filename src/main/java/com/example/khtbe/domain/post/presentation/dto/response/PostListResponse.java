package com.example.khtbe.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.PrimitiveIterator;

@Getter
@RequiredArgsConstructor
public class PostListResponse {
    private final List<PostResponse> postResponses;

    @Getter
    @Builder
    public static class PostResponse {
        private final Long id;
        private final String userNickname;
        private final String profile;
        private final String title;
        private final String content;
        private final String path;
    }
}
