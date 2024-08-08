package com.example.khtbe.domain.post.service;

import com.example.khtbe.domain.post.domain.Post;
import com.example.khtbe.domain.post.domain.repository.PostRepository;
import com.example.khtbe.domain.post.presentation.dto.response.PostListResponse;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostListService {

    private final PostRepository postRepository;
    private final S3Util s3Util;

    public List<PostListResponse.PostResponse> getPostList() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(this::ofPostResponse)
                .collect(Collectors.toList());
    }

    private PostListResponse.PostResponse ofPostResponse(Post post) {
        return PostListResponse.PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userNickname(post.getUser().getName())
                .profile(s3Util.getProfileImageUrl(post.getUser().getPath()))
                .build();
    }
}
