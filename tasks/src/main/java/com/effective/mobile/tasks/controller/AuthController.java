package com.effective.mobile.tasks.controller;

import com.effective.mobile.tasks.dto.JwtAuthenticationResponse;
import com.effective.mobile.tasks.dto.SignInRequest;
import com.effective.mobile.tasks.dto.SignUpRequest;
import com.effective.mobile.tasks.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }
}
