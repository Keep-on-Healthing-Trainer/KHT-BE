package com.example.khtbe.domain.post.presentation.dto.response;


import com.example.khtbe.domain.comment.presentation.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class PostResponse {

    private final Long id;
    private final String userNickname;
    private final String profileImage;
    private final String title;
    private final String content;
    private final String file;
    private final List<CommentResponse> comments;

    @Builder
    private PostResponse(Long id, String userNickname, String profileImage, String title, String content, String file, List<CommentResponse> comments) {
        this.id = id;
        this.userNickname = userNickname;
        this.profileImage = profileImage;
        this.title = title;
        this.content = content;
        this.file = file;
        this.comments = comments;
    }
}
