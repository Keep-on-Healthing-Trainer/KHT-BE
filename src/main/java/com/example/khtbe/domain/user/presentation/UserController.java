package com.example.khtbe.domain.user.presentation;

import com.example.khtbe.domain.user.presentation.dto.request.LoginRequest;
import com.example.khtbe.domain.user.presentation.dto.request.SignupRequest;
import com.example.khtbe.domain.user.presentation.dto.response.TokenResponse;
import com.example.khtbe.domain.user.service.UserLoginService;
import com.example.khtbe.domain.user.service.UserSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserSignupService userSignupService;
    private final UserLoginService userLoginService;

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
}
