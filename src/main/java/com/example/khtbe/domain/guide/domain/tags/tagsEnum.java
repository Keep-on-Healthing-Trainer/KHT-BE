package com.example.khtbe.domain.guide.domain.tags;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum tagsEnum {
    CARDIO("유산소"),
    SHOULDER("어깨"),
    ARM("팔"),
    ABDOMINAL("복근"),
    BACK("등"),
    THIGH("허벅지"),
    CALF("종아리");

    private final String tags;
}
