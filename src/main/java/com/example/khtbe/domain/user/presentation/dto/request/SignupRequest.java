package com.example.khtbe.domain.user.presentation.dto.request;

import com.example.khtbe.domain.guide.domain.tags.tagsEnum;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
public class SignupRequest {
    @NotNull
    @Size(min = 6, max = 15)
    private String userId;

    @NotNull
    @Size(max = 8)
    private String name;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @NotNull
    @Size(max = 11)
    private String phoneNumber;

    private Set<tagsEnum> uncomfortableParts;
}
