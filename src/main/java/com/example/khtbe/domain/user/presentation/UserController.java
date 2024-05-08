package com.example.khtbe.domain.user.presentation;

import com.example.khtbe.domain.user.domain.User;
import com.example.khtbe.domain.user.presentation.dto.request.LoginRequest;
import com.example.khtbe.domain.user.presentation.dto.request.SignupRequest;
import com.example.khtbe.domain.user.presentation.dto.response.TokenResponse;
import com.example.khtbe.domain.user.presentation.dto.response.UserDetailResponse;
import com.example.khtbe.domain.user.service.TokenRefreshService;
import com.example.khtbe.domain.user.service.UserLoginService;
import com.example.khtbe.domain.user.service.UserService;
import com.example.khtbe.domain.user.service.UserSignupService;
import com.example.khtbe.domain.user.service.util.UserUtil;
import com.example.khtbe.infra.aws.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserSignupService userSignupService;
    private final UserLoginService userLoginService;
    private final TokenRefreshService tokenRefreshService;
    private final UserService userService;
    private final UserUtil userUtil;
    private final S3Util s3Util;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Valid SignupRequest request) {
        userSignupService.signup(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody LoginRequest request){
        return userLoginService.login(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshService.refresh(refreshToken);
    }

    @GetMapping
    public UserDetailResponse getUser() {
        return userService.getUser();
    }

    @PostMapping(value = "/modifyProfile/{userId}", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailResponse profileImage(@PathVariable String userId, @RequestPart(value = "image", required = false) MultipartFile file) {
        userUtil.upload(userId, file);
        return userService.getUser();
    }
}
