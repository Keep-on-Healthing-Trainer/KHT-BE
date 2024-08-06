package com.example.khtbe.domain.guide.domain.repository;

import com.example.khtbe.domain.guide.domain.Guide;
import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    @Query("SELECT g FROM Guide g JOIN g.tags t WHERE g.title LIKE %:keyword% OR t = :tagName")
    List<Guide> findByTitleOrTag(@Param("keyword") String keyword, @Param("tagName") tagsEnum tagName);
}
