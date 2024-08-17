package com.example.khtbe.domain.guide.presentation;

import com.example.khtbe.domain.guide.domain.Guide;
import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import com.example.khtbe.domain.guide.presentation.dto.request.GuideRequest;
import com.example.khtbe.domain.guide.presentation.dto.response.ReturnGuideIdResponse;
import com.example.khtbe.domain.guide.service.GuideService;
import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/guide")
@RestController
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnGuideIdResponse create(@RequestBody @Valid GuideRequest request) {
        return guideService.create(request);
    }

    @GetMapping("/search")
    public List<Guide> findPost(@RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "tag", required = false) String tag) {
        tagsEnum tagEnum = null;

        if (tag != null) {
            try {
                tagEnum = tagsEnum.valueOf(tag);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 태그입니다.");
            }
        }

        return guideService.findGuidesByTitleAndTag(title, tagEnum);
    }

    @GetMapping("/recommend")
    public List<Guide> findRecommendedGuides() {
        return guideService.findRecommendedGuides();
    }
}
