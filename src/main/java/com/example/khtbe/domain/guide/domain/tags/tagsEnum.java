package com.example.khtbe.domain.guide.domain.tags;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum tagsEnum {
    유산소("유산소"), 어깨("어깨"), 팔("팔"), 복근("복근"), 등("등"), 허벅지("허벅지"), 종아리("종아리");

    private final String major;
}
