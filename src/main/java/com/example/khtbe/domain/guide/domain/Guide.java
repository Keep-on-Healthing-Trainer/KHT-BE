package com.example.khtbe.domain.guide.domain;

import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Getter
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
    private Set<tagsEnum> tags;

    private String path;

    @Builder
    public Guide(String title, String startPosture, String exerciseMethod, String warning, String introduction, String path){
        this.title = title;
        this.startPosture = startPosture;
        this.warning = warning;
        this.exerciseMethod = exerciseMethod;
        this.introduction = introduction;
        this.path = path;
    }
}
