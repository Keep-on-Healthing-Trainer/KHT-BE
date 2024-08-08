package com.example.khtbe.domain.post.service;

import com.example.khtbe.domain.comment.presentation.dto.response.CommentResponse;
import com.example.khtbe.domain.post.domain.Post;
import com.example.khtbe.domain.post.domain.repository.PostRepository;
import com.example.khtbe.domain.post.exception.PostNotFoundException;
import com.example.khtbe.domain.post.presentation.dto.response.PostResponse;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostDetailsService {

	private final PostRepository postRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public PostResponse getPostDetails(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

		return PostResponse.builder()
			.id(post.getId())
			.userNickname(post.getUser().getName())
			.profileImage(s3Util.getProfileImageUrl(post.getUser().getPath()))
			.title(post.getTitle())
			.content(post.getContent())
			.file(s3Util.getPostImageUrl(post.getPath()))
			.comments(post.getComments().stream().map(comment -> {
					return CommentResponse.builder()
						.id(comment.getId())
						.userNickname(comment.getUser().getName())
							.profileImage(s3Util.getProfileImageUrl(comment.getUser().getPath()))
						.content(comment.getContent())
						.build();
				}
			).collect(Collectors.toList()))
			.build();
	}

}
