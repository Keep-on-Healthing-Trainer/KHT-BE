package com.example.khtbe.domain.post.service;

import com.example.khtbe.domain.post.domain.Post;
import com.example.khtbe.domain.post.domain.repository.PostRepository;
import com.example.khtbe.domain.post.exception.PostNotFoundException;
import com.example.khtbe.domain.post.presentation.dto.request.PostRequest;
import com.example.khtbe.domain.post.presentation.dto.response.ReturnPostIdResponse;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final S3Util s3Util;
    private final UserUtil userUtil;

    @Transactional
    public ReturnPostIdResponse create(PostRequest request) {
        Post post = postRepository.save(Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(userUtil.getUser())
                .build());

        return new ReturnPostIdResponse(post.getId());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if (!post.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotFoundException.EXCEPTION;
        if (post.getPath() != null) s3Util.delete(post.getPath());

        postRepository.delete(post);
    }

    @Transactional
    public void postImage(Long id, MultipartFile file) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotFoundException.EXCEPTION;
        if (post.getPath() != null) s3Util.delete(post.getPath());

        post.updatePath(s3Util.upload(file));
    }

    @Transactional(readOnly = true)
    public int getCommentCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return post.getCommentCount();
    }
}
