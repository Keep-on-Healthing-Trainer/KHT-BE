package com.example.khtbe.domain.post.presentation;

import com.example.khtbe.domain.post.presentation.dto.request.PostRequest;
import com.example.khtbe.domain.post.presentation.dto.response.PostListResponse;
import com.example.khtbe.domain.post.presentation.dto.response.PostResponse;
import com.example.khtbe.domain.post.presentation.dto.response.ReturnPostIdResponse;
import com.example.khtbe.domain.post.service.PostDetailsService;
import com.example.khtbe.domain.post.service.PostListService;
import com.example.khtbe.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostDetailsService postDetailsService;
    private final PostListService postListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnPostIdResponse create(@RequestBody @Valid PostRequest request) {
        return postService.create(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostDetails(@PathVariable @NotNull Long id) {
        return postDetailsService.getPostDetails(id);
    }

    @PostMapping(value = "/postImage/{id}", consumes = "multipart/form-data")
    public void postImage(@PathVariable @NotNull Long id, @RequestPart(value = "image") MultipartFile file) {
        postService.postImage(id, file);
    }

    @GetMapping("/list")
    public List<PostListResponse.PostResponse> getPostList() {
        return postListService.getPostList();
    }
}
