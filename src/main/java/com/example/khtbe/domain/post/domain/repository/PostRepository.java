package com.example.khtbe.domain.post.domain.repository;

import com.example.khtbe.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
}
