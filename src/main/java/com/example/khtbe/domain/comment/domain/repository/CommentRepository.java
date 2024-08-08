package com.example.khtbe.domain.comment.domain.repository;

import com.example.khtbe.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    
}
