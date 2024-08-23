package com.example.khtbe.domain.guide.service;

import com.example.khtbe.domain.guide.domain.Guide;
import com.example.khtbe.domain.guide.domain.repository.GuideRepository;
import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import com.example.khtbe.domain.guide.presentation.dto.request.GuideRequest;
import com.example.khtbe.domain.guide.presentation.dto.response.ReturnGuideIdResponse;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideService {
    private final GuideRepository guideRepository;
    private final UserUtil userUtil;

    @Transactional
    public ReturnGuideIdResponse create(GuideRequest request) {
        Guide guide = guideRepository.save(Guide.builder()
                .title(request.getTitle())
                .startPosture(request.getStartPosture())
                .exerciseMethod(request.getExerciseMethod())
                .warning(request.getWarning())
                .introduction(request.getIntroduction())
                .path(request.getPath())
                .tags(Set.copyOf(request.getTags()))
                .thumbnail(request.getThumbnail())
                .build());

        return new ReturnGuideIdResponse(guide.getId());
    }

    public List<Guide> findGuidesByTitleAndTag(String keyword, tagsEnum tagName) {
        if (tagName == null) {
            return guideRepository.findByTitle(keyword);
        }
        return guideRepository.findByTitleAndTag(keyword, tagName);
    }

    public List<Guide> findRecommendedGuides() {
        User user = userUtil.getUser();
        Set<tagsEnum> uncomfortableParts = user.getUncomfortableParts();

        List<Guide> allGuides = guideRepository.findGuidesExcludingUncomfortableParts(uncomfortableParts);

        Collections.shuffle(allGuides);
        return allGuides.stream().limit(6).collect(Collectors.toList());
    }
}

