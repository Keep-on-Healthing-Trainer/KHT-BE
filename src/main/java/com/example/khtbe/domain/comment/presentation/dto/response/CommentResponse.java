package com.example.khtbe.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {

    private final Long id;
    private final String userNickname;
    private final String profileImage;
    private final String content;

    @Builder
    public CommentResponse(Long id, String userNickname, String profileImage, String content, String createDate) {
        this.id = id;
        this.userNickname = userNickname;
        this.profileImage = profileImage;
        this.content = content;
    }

}
