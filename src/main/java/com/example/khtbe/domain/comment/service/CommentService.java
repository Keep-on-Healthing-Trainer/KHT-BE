package com.example.khtbe.domain.comment.service;

import com.example.khtbe.domain.comment.domain.Comment;
import com.example.khtbe.domain.comment.domain.repository.CommentRepository;
import com.example.khtbe.domain.comment.exception.CommentNotFoundException;
import com.example.khtbe.domain.comment.presentation.dto.request.CommentRequest;
import com.example.khtbe.domain.post.domain.Post;
import com.example.khtbe.domain.post.domain.repository.PostRepository;
import com.example.khtbe.domain.post.exception.PostNotFoundException;
import com.example.khtbe.domain.user.service.exception.UserNotFoundException;
import com.example.khtbe.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public Long creat(CommentRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return commentRepository.save(Comment.builder()
                .post(post)
                .user(userUtil.getUser())
                .content(request.getContent())
                .build()).getId();

    }

    @Transactional
    public Long update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        if(!comment.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotFoundException.EXCEPTION;

        return comment.update(request.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        if(!comment.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotFoundException.EXCEPTION;

        commentRepository.delete(comment);
    }
}
