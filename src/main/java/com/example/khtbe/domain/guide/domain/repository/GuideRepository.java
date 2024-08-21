package com.example.khtbe.domain.guide.domain.repository;

import com.example.khtbe.domain.guide.domain.Guide;
import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    @Query("SELECT g FROM Guide g JOIN g.tags t WHERE g.title LIKE %:keyword% AND t = :tagName")
    List<Guide> findByTitleAndTag(@Param("keyword") String keyword, @Param("tagName") tagsEnum tagName);

    @Query("SELECT g FROM Guide g WHERE g.title LIKE %:keyword%")
    List<Guide> findByTitle(@Param("keyword") String keyword);

    @Query("SELECT g FROM Guide g WHERE g NOT IN " +
            "(SELECT g FROM Guide g JOIN g.tags t WHERE t IN :uncomfortableParts) " +
            "ORDER BY RAND()")
    List<Guide> findRandomRecommendedGuides(@Param("uncomfortableParts") Set<tagsEnum> uncomfortableParts);
}
