package com.example.khtbe.domain.guide.presentation.dto.request;

import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class GuideRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String startPosture;

    @NotBlank
    private String exerciseMethod;

    @NotBlank 
    private String warning;

    @NotBlank
    private String introduction;

    private String path;

    private String thumbnail;

    private List<tagsEnum> tags;
}
