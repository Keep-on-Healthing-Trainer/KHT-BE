package com.example.khtbe.domain.guide.domain;

import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@RequiredArgsConstructor
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String startPosture;

    @Column(nullable = false)
    private String exerciseMethod;

    @Column(nullable = false)
    private String warning;

    @Column(nullable = false)
    private String introduction;

    @ElementCollection(targetClass = tagsEnum.class)
    @Enumerated(EnumType.STRING)
    private Set<tagsEnum> tags;

    private String path;

    @Builder
    public Guide(String title, String startPosture, String exerciseMethod, String warning, String introduction, String path, Set<tagsEnum> tags){
        this.title = title;
        this.startPosture = startPosture;
        this.exerciseMethod = exerciseMethod;
        this.warning = warning;
        this.introduction = introduction;
        this.path = path;
        this.tags = tags;
    }
}
